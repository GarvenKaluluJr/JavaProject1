package com.project1.taskapi.service.impl;

import com.project1.taskapi.model.Task;
import com.project1.taskapi.repository.TaskRepository;
import com.project1.taskapi.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Cacheable(value = "tasks", key = "#userId")
    public List<Task> getAllTasks(UUID userId) {
        log.info("Fetching tasks for user {} from DB (not cache)", userId);
        return taskRepository.findByUserIdAndDeletedFalse(userId);
    }

    @Override
    @Cacheable(value = "pendingTasks", key = "#userId")
    public List<Task> getPendingTasks(UUID userId) {
        return taskRepository.findByUserIdAndCompletedFalseAndDeletedFalse(userId);
    }

    @Override
    @CacheEvict(value = {"tasks", "pendingTasks"}, key = "#task.userId")
    public Task addTask(Task task) {
        task.setCompleted(false);
        task.setDeleted(false);
        return taskRepository.save(task);
    }

    @Override
    @CacheEvict(value = {"tasks", "pendingTasks"}, allEntries = true)
    public void deleteTask(UUID taskId) {
        Optional<Task> opt = taskRepository.findById(taskId);
        if (opt.isPresent()) {
            Task task = opt.get();
            task.setDeleted(true);
            taskRepository.save(task);
        }
    }

    @Override
    @CacheEvict(value = {"tasks", "pendingTasks"}, key = "#userId")
    public Task markTaskCompleted(UUID taskId, UUID userId) {
        Optional<Task> opt = taskRepository.findById(taskId);
        if (opt.isPresent()) {
            Task task = opt.get();
            task.setCompleted(true);
            return taskRepository.save(task);
        }
        throw new RuntimeException("Task not found");
    }

    @Override
    @CacheEvict(value = {"tasks", "pendingTasks"}, allEntries = true)
    public void evictAllCaches() {
        // No-op
    }
}
