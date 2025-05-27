package com.project1.taskapi.repository;

import com.project1.taskapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByUserIdAndDeletedFalse(UUID userId);
    List<Task> findByUserIdAndCompletedFalseAndDeletedFalse(UUID userId);
    List<Task> findByTargetDateBeforeAndCompletedFalseAndDeletedFalse(LocalDateTime now);
}