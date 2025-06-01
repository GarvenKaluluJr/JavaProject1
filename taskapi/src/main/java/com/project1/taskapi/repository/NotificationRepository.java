package com.project1.taskapi.repository;

import com.project1.taskapi.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    List<Notification> findByUserId(UUID userId);
    List<Notification> findByUserIdAndReadFalse(UUID userId);
}