package com.project1.taskapi.service.impl;

import com.project1.taskapi.model.Notification;
import com.project1.taskapi.service.NotificationService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Profile("memory")
public class NotificationServiceImpl implements NotificationService {

    private final Map<UUID, Notification> notifications = new HashMap<>();

    @Override
    public List<Notification> getAllNotifications(UUID userId) {
        List<Notification> result = new ArrayList<>();
        for (Notification n : notifications.values()) {
            if (n.getUserId().equals(userId)) {
                result.add(n);
            }
        }
        return result;
    }

    @Override
    public List<Notification> getPendingNotifications(UUID userId) {
        List<Notification> result = new ArrayList<>();
        for (Notification n : notifications.values()) {
            if (n.getUserId().equals(userId) && !n.isRead()) {
                result.add(n);
            }
        }
        return result;
    }

    @Override
    public Notification addNotification(Notification notification) {
        UUID id = UUID.randomUUID();
        notification.setId(id);
        notifications.put(id, notification);
        return notification;
    }
}
