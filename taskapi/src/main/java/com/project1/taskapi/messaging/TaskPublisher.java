package com.project1.taskapi.messaging;

import com.project1.taskapi.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("postgres")
public class TaskPublisher {

    private final RabbitTemplate rabbitTemplate;

    public TaskPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendTask(TaskMessage taskMessage) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.TASK_QUEUE, taskMessage);
    }
}
