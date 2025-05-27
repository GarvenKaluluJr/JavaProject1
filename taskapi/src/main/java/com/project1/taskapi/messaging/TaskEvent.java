package com.project1.taskapi.messaging;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class TaskEvent implements Serializable {
    private UUID id;
    private UUID userId;
    private String description;
    private LocalDateTime creationDate;

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }
}
