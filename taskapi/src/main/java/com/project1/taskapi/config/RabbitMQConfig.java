package com.project1.taskapi.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String TASK_QUEUE = "task.queue";

    @Bean
    public Queue taskQueue() {
        return new Queue(TASK_QUEUE, false);
    }
}
