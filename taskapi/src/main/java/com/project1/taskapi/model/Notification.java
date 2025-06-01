package com.project1.taskapi.model;

import jakarta.persistence.*;
<<<<<<< Updated upstream
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "notifications")
=======
import java.util.UUID;

@Entity
@Table(name = "notification")
>>>>>>> Stashed changes
public class Notification {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    private String message;

    private boolean read;

<<<<<<< Updated upstream
    // Getters and setters
=======
    public Notification() {}

    public Notification(UUID id, UUID userId, String message, boolean read) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.read = read;
    }

>>>>>>> Stashed changes
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isRead() { return read; }
    public void setRead(boolean read) { this.read = read; }
}
