package com.project1.taskapi.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "task")
public class Task {
    @Id
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    private String description;
    private boolean completed;
    private boolean deleted;

    public Task() {}

    public Task(UUID id, UUID userId, String description, boolean completed, boolean deleted) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.completed = completed;
        this.deleted = deleted;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }
}
