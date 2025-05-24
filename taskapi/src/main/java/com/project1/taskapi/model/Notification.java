package com.project1.taskapi.model;

import java.util.UUID;

public class Notification {
    private UUID id;
    private UUID userId;
    private String message;
    private boolean read;

    // --- Constructors ---

    public Notification() {
    }

    public Notification(UUID id, UUID userId, String message, boolean read) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.read = read;
    }

    // --- Getters and Setters ---

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
