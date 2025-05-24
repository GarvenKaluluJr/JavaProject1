package com.project1.taskapi.controller;

import com.project1.taskapi.model.Notification;
import com.project1.taskapi.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    public NotificationController(NotificationService notificationService) { this.notificationService = notificationService; }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Notification>> getAll(@PathVariable UUID userId) {
        return ResponseEntity.ok(notificationService.getAllNotifications(userId));
    }

    @GetMapping("/{userId}/pending")
    public ResponseEntity<List<Notification>> getPending(@PathVariable UUID userId) {
        return ResponseEntity.ok(notificationService.getPendingNotifications(userId));
    }
}
