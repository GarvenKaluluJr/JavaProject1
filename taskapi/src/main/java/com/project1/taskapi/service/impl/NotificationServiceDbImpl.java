package com.project1.taskapi.service.impl;

import com.project1.taskapi.model.Notification;
import com.project1.taskapi.repository.NotificationRepository;
import com.project1.taskapi.service.NotificationService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Profile({"postgres", "redis"})
public class NotificationServiceDbImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    public NotificationServiceDbImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    @Override
    public List<Notification> getAllNotifications(UUID userId) {
        return notificationRepository.findByUserId(userId);
    }
    @Override
    public List<Notification> getPendingNotifications(UUID userId) {
        return notificationRepository.findByUserIdAndReadFalse(userId);
    }
    @Override
    public Notification addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
}
