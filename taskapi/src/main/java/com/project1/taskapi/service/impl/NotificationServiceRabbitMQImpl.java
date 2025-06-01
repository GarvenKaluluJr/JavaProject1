package com.project1.taskapi.service.impl;

import com.project1.taskapi.model.Notification;
import com.project1.taskapi.messaging.TaskMessage;
import com.project1.taskapi.repository.NotificationRepository;
import com.project1.taskapi.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@Profile("rabbit")
public class NotificationServiceRabbitMQImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceRabbitMQImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // This will ONLY get called by RabbitMQ Listener, not directly
    @RabbitListener(queues = "task.queue")
    public void processTaskMessage(TaskMessage message) {
        Notification notification = new Notification();
        notification.setId(UUID.randomUUID());
        notification.setUserId(message.getUserId());
        notification.setMessage("Task created: " + message.getDescription());
        notification.setRead(false);
        notificationRepository.save(notification);
    }

    // Implement the interface but do nothing for these methods
    @Override public java.util.List<Notification> getAllNotifications(UUID userId) { return notificationRepository.findByUserId(userId); }
    @Override public java.util.List<Notification> getPendingNotifications(UUID userId) { return notificationRepository.findByUserIdAndReadFalse(userId); }
    @Override public Notification addNotification(Notification notification) { return notificationRepository.save(notification); }
}
