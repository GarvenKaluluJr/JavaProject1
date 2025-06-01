package com.project1.taskapi.service.impl;

import com.project1.taskapi.model.Task;
import com.project1.taskapi.repository.TaskRepository;
import com.project1.taskapi.service.TaskService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Profile("redis")
public class TaskServiceRedisImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceRedisImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Cacheable(value = "tasks", key = "#userId")
    public List<Task> getAllTasks(UUID userId) {
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
        task.setDeleted(false);
        task.setCompleted(false);
        return taskRepository.save(task);
    }

    @Override
    @CacheEvict(value = {"tasks", "pendingTasks"}, allEntries = true)
    public void deleteTask(UUID taskId) {
        taskRepository.findById(taskId).ifPresent(task -> {
            task.setDeleted(true);
            taskRepository.save(task);
        });
    }
}
