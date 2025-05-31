package com.project1.taskapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import java.util.UUID;

@Entity
public class Notification {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    private String message;
    private boolean read;

    // Constructors
    public Notification() {}

    public Notification(UUID id, UUID userId, String message, boolean read) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.read = read;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isRead() { return read; }
    public void setRead(boolean read) { this.read = read; }
}
