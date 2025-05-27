package com.project1.taskapi.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String TASK_EXCHANGE = "task.exchange";
    public static final String TASK_QUEUE = "task.notifications.queue";
    public static final String TASK_ROUTING_KEY = "task.created";

    @Bean
    public DirectExchange taskExchange() {
        return new DirectExchange(TASK_EXCHANGE);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(TASK_QUEUE, true);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(notificationQueue())
                .to(taskExchange())
                .with(TASK_ROUTING_KEY);
    }
}
