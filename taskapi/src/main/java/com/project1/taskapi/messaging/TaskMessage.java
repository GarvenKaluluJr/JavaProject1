package com.project1.taskapi.messaging;

import java.io.Serializable;
import java.util.UUID;

public class TaskMessage implements Serializable {
    private UUID userId;
    private String description;
    private UUID taskId;

    // Getters and setters
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public UUID getTaskId() { return taskId; }
    public void setTaskId(UUID taskId) { this.taskId = taskId; }
}
