package com.taller.entrega;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntregaService {

    @Autowired
    private EnvioRepository envioRepository;

    @RabbitListener(queues = "entrega.queue")
    public void procesarNuevoCliente(ClienteCreadoEvent evento) {
        System.out.println("¡Llegó un mensaje! Cliente: " + evento.getNombre());
        Envio envio = new Envio();
        envio.setIdClienteOriginal(evento.getIdCliente());
        envio.setEstado("PENDIENTE_DE_ENVIO");
        envio.setDireccion("Pendiente");
        envioRepository.save(envio);
        System.out.println("Envío guardado en BD.");
    }
}