package com.project1.taskapi.service.impl;

import com.project1.taskapi.model.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceImplTest {

    private NotificationServiceImpl notificationService;
    private UUID userId;

    @BeforeEach
    void setUp() {
        notificationService = new NotificationServiceImpl();
        userId = UUID.randomUUID();
    }

    @Test
    void testAddNotificationAndGetAll() {
        Notification n = new Notification();
        n.setUserId(userId);
        n.setMessage("Hello");
        notificationService.addNotification(n);

        List<Notification> all = notificationService.getAllNotifications(userId);
        assertEquals(1, all.size());
        assertEquals("Hello", all.get(0).getMessage());
    }

    @Test
    void testGetPendingNotifications() {
        Notification n = new Notification();
        n.setUserId(userId);
        n.setMessage("Pending notification");
        n.setRead(false);
        notificationService.addNotification(n);

        List<Notification> pending = notificationService.getPendingNotifications(userId);
        assertEquals(1, pending.size());

        // Mark as read and check again
        n.setRead(true);
        pending = notificationService.getPendingNotifications(userId);
        assertEquals(0, pending.size());
    }

    @Test
    void testAddMultipleNotifications() {
        Notification n1 = new Notification();
        n1.setUserId(userId);
        n1.setMessage("1");
        n1.setRead(false);

        Notification n2 = new Notification();
        n2.setUserId(userId);
        n2.setMessage("2");
        n2.setRead(false);

        notificationService.addNotification(n1);
        notificationService.addNotification(n2);

        List<Notification> all = notificationService.getAllNotifications(userId);
        assertEquals(2, all.size());
    }
}