package com.taller.entrega;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntregaService {

    @Autowired
    private EnvioRepository envioRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ENTREGA)
    public void procesarNuevoCliente(ClienteCreadoEvent evento) {
        System.out.println("¡Llegó un mensaje! Cliente: " + evento.getNombre());
        Envio envio = new Envio();
        envio.setIdClienteOriginal(Long.valueOf(evento.getClienteId()));
        envio.setEstado("PENDIENTE_DE_ENVIO");
        envio.setDireccion("Pendiente");
        Envio envioGuardado = envioRepository.save(envio);
        System.out.println("Envío guardado en BD.");

        EnvioBienvenidaCreadoEvent envioCreadoEvent = new EnvioBienvenidaCreadoEvent(
                String.valueOf(envioGuardado.getId()),
                evento.getClienteId(),
                evento.getNombre(),
                evento.getEmail(),
                envioGuardado.getEstado(),
                envioGuardado.getDireccion());

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_CLIENTES,
                RabbitMQConfig.ROUTING_KEY_ENVIO_BIENVENIDA_CREADO,
                envioCreadoEvent);
        System.out.println("Evento publicado: envio.bienvenida.creado");
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ENTREGA_ESTADO)
    public void procesarEnvioEnviado(EnvioEstadoActualizadoEvent evento) {
        if (!"ENVIADO".equalsIgnoreCase(evento.getEstado())) {
            System.out.println("Evento de estado ignorado: " + evento.getEstado());
            return;
        }

        Long envioId = Long.valueOf(evento.getEnvioId());
        Envio envio = envioRepository.findById(envioId)
                .orElseThrow(() -> new IllegalArgumentException("Envio no encontrado: " + envioId));

        envio.setEstado("ENVIADO");
        envioRepository.save(envio);
        System.out.println("Envio actualizado a ENVIADO: " + envioId);

        envio.setEstado("LEIDO");
        Envio envioLeido = envioRepository.save(envio);
        System.out.println("Envio actualizado a LEIDO: " + envioId);

        EnvioEstadoActualizadoEvent leidoEvent = new EnvioEstadoActualizadoEvent(
                String.valueOf(envioLeido.getId()),
                evento.getClienteId(),
                envioLeido.getEstado(),
                evento.getEmail(),
                evento.getNombre());

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_CLIENTES,
                RabbitMQConfig.ROUTING_KEY_ENVIO_BIENVENIDA_LEIDO,
                leidoEvent);
        System.out.println("Evento publicado: envio.bienvenida.leido");
    }
}