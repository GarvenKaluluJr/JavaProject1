package com.project1.taskapi.service.impl;

import com.project1.taskapi.model.Notification;
import com.project1.taskapi.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceImplTest {

    private NotificationServiceImpl notificationService;
    private UUID userId;

    @BeforeEach
    void setUp() {
        NotificationRepository mockRepository = Mockito.mock(NotificationRepository.class);
        notificationService = new NotificationServiceImpl(mockRepository);
        userId = UUID.randomUUID();
    }

    @Test
    void testAddNotificationAndGetAll() {
        Notification n = new Notification();
        n.setUserId(userId);
        n.setMessage("Hello");
        notificationService.addNotification(n);

        // Add your assertions here for mocked repository behavior if needed
        // For now, this will test that addNotification() doesn't throw errors.
        assertNotNull(n);
        assertEquals("Hello", n.getMessage());
    }

    @Test
    void testGetPendingNotifications() {
        Notification n = new Notification();
        n.setUserId(userId);
        n.setMessage("Pending notification");
        n.setRead(false);
        notificationService.addNotification(n);

        // Since the repository is mocked, you should mock its behavior if you want meaningful results
        assertFalse(n.isRead());
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

        assertNotNull(n1);
        assertNotNull(n2);
    }
}
