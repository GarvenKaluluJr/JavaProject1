package com.project1.taskapi.service.impl;

import com.project1.taskapi.model.Notification;
import com.project1.taskapi.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationServiceImplTest {

    private NotificationServiceImpl notificationService;
    private NotificationRepository notificationRepository;
    private UUID userId;

    @BeforeEach
    void setUp() {
        notificationRepository = mock(NotificationRepository.class);
        notificationService = new NotificationServiceImpl(notificationRepository);
        userId = UUID.randomUUID();
    }

    @Test
    void testAddNotificationAndGetAll() {
        Notification n = new Notification();
        n.setUserId(userId);
        n.setMessage("Hello");

        Notification savedN = new Notification();
        savedN.setId(UUID.randomUUID());
        savedN.setUserId(userId);
        savedN.setMessage("Hello");

        when(notificationRepository.save(any(Notification.class))).thenReturn(savedN);
        when(notificationRepository.findByUserId(userId)).thenReturn(List.of(savedN));

        notificationService.addNotification(n);
        List<Notification> all = notificationService.getAllNotifications(userId);
        assertEquals(1, all.size());
        assertEquals("Hello", all.get(0).getMessage());

        verify(notificationRepository, times(1)).save(any(Notification.class));
        verify(notificationRepository, times(1)).findByUserId(userId);
    }

    @Test
    void testGetPendingNotifications() {
        Notification n = new Notification();
        n.setUserId(userId);
        n.setMessage("Pending notification");
        n.setRead(false);

        when(notificationRepository.findByUserIdAndReadFalse(userId)).thenReturn(List.of(n));

        List<Notification> pending = notificationService.getPendingNotifications(userId);
        assertEquals(1, pending.size());
        assertFalse(pending.get(0).isRead());

        verify(notificationRepository, times(1)).findByUserIdAndReadFalse(userId);
    }

    @Test
    void testAddMultipleNotifications() {
        Notification n1 = new Notification();
        n1.setUserId(userId);
        n1.setMessage("1");

        Notification n2 = new Notification();
        n2.setUserId(userId);
        n2.setMessage("2");

        when(notificationRepository.findByUserId(userId)).thenReturn(Arrays.asList(n1, n2));

        // Add both (simulate save doesn't matter for this test)
        notificationService.addNotification(n1);
        notificationService.addNotification(n2);

        List<Notification> all = notificationService.getAllNotifications(userId);
        assertEquals(2, all.size());

        verify(notificationRepository, times(2)).save(any(Notification.class));
        verify(notificationRepository, times(1)).findByUserId(userId);
    }
}
