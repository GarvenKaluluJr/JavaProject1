package com.project1.taskapi.service.impl;

import com.project1.taskapi.model.Task;
import com.project1.taskapi.repository.TaskRepository;
import com.project1.taskapi.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks(UUID userId) {
        return taskRepository.findByUserIdAndDeletedFalse(userId);
    }

    @Override
    public List<Task> getPendingTasks(UUID userId) {
        return taskRepository.findByUserIdAndCompletedFalseAndDeletedFalse(userId);
    }

    @Override
    public Task addTask(Task task) {
        task.setDeleted(false);
        task.setCompleted(false);
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(UUID taskId) {
        taskRepository.findById(taskId).ifPresent(task -> {
            task.setDeleted(true);
            taskRepository.save(task);
        });
    }
}
