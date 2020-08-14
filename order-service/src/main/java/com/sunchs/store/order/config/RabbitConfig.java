package com.sunchs.store.order.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue rabbitQueue() {
        return new Queue("king.test333");
    }

    @Bean
    public DirectExchange rabbitExchange() {
        return new DirectExchange("king.amq.fanout",true,false);
    }

    @Bean
    public Binding bindingDirect() {
        return BindingBuilder.bind(rabbitQueue()).to(rabbitExchange()).with("");
    }

}