package com.project1.taskapi.service;

import com.project1.taskapi.model.Task;
import com.project1.taskapi.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TaskSchedulerService {

    private static final Logger logger = LoggerFactory.getLogger(TaskSchedulerService.class);

    private final TaskRepository taskRepository;

    public TaskSchedulerService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Every minute, check for overdue tasks
    @Scheduled(cron = "0 * * * * *") // every minute, at 0 seconds
    @Transactional
    public void checkForOverdueTasks() {
        LocalDateTime now = LocalDateTime.now();
        List<Task> overdueTasks = taskRepository.findByTargetDateBeforeAndCompletedFalseAndDeletedFalse(now);

        if (!overdueTasks.isEmpty()) {
            logger.info("Found {} overdue tasks", overdueTasks.size());
            overdueTasks.forEach(task -> {
                logger.warn("Overdue Task: {} for user {}", task.getDescription(), task.getUserId());
            });
        } else {
            logger.info("No overdue tasks found at this time.");
        }
    }
    @Async
    public void notifyUserAsync(UUID userId, String message) {
        // Simulate sending notification (email, push, etc.)
        logger.info("Sending notification to user {}: {}", userId, message);
    }
}
