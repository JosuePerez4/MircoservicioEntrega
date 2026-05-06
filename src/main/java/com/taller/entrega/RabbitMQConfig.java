package com.taller.entrega;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_CLIENTES = "cliente-events-exchange";
    public static final String QUEUE_ENTREGA = "entrega-queue";
    public static final String QUEUE_ENTREGA_ESTADO = "entrega-estado-queue";
    public static final String ROUTING_KEY_CLIENTE_CREADO = "cliente.creado";
    public static final String ROUTING_KEY_ENVIO_BIENVENIDA_CREADO = "envio.bienvenida.creado";
    public static final String ROUTING_KEY_ENVIO_BIENVENIDA_ENVIADO = "envio.bienvenida.enviado";
    public static final String ROUTING_KEY_ENVIO_BIENVENIDA_LEIDO = "envio.bienvenida.leido";

    @Bean
    public TopicExchange clienteExchange() {
        return new TopicExchange(EXCHANGE_CLIENTES, true, false);
    }

    @Bean
    public Queue entregaQueue() {
        return new Queue(QUEUE_ENTREGA, true, false, false);
    }

    @Bean
    public Queue entregaEstadoQueue() {
        return new Queue(QUEUE_ENTREGA_ESTADO, true, false, false);
    }

    @Bean
    public Binding clienteCreadoBinding(Queue entregaQueue, TopicExchange clienteExchange) {
        return BindingBuilder.bind(entregaQueue).to(clienteExchange).with(ROUTING_KEY_CLIENTE_CREADO);
    }

    @Bean
    public Binding envioEnviadoBinding(Queue entregaEstadoQueue, TopicExchange clienteExchange) {
        return BindingBuilder
                .bind(entregaEstadoQueue)
                .to(clienteExchange)
                .with(ROUTING_KEY_ENVIO_BIENVENIDA_ENVIADO);
    }

    @Bean
    public MessageConverter jsonMessageConverter() { return new JacksonJsonMessageConverter(); }
}