package com.taller.entrega;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public FanoutExchange fanoutExchange() { return new FanoutExchange("cliente.creado.exchange"); }

    @Bean
    public Queue entregaQueue() { return new Queue("entrega.queue", true); }

    @Bean
    public Binding binding(Queue entregaQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(entregaQueue).to(fanoutExchange);
    }

    @Bean
    public MessageConverter jsonMessageConverter() { return new JacksonJsonMessageConverter(); }
}