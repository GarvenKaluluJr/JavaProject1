package com.project1.taskapi.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    private String message;
    private boolean read;

    public Notification() {}

    public Notification(UUID id, UUID userId, String message, boolean read) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.read = read;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isRead() { return read; }
    public void setRead(boolean read) { this.read = read; }
}
