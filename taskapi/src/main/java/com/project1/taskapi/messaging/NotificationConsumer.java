package com.project1.taskapi.messaging;

import com.project1.taskapi.model.Notification;
import com.project1.taskapi.repository.NotificationRepository;
import com.project1.taskapi.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;

    public NotificationConsumer(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.TASK_QUEUE)
    public void handleTaskCreated(TaskEvent event) {
        Notification notification = new Notification();
        notification.setUserId(event.getUserId());
        notification.setMessage("New Task: " + event.getDescription());
        notification.setRead(false);
        notificationRepository.save(notification);
    }
}