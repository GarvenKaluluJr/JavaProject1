package com.project1.taskapi.service.impl;

import com.project1.taskapi.model.Task;
import com.project1.taskapi.service.TaskService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Profile("rabbit")
public class TaskServiceRabbitImpl implements TaskService {
    private final Map<UUID, Task> tasks = new HashMap<>();

    @Override
    public List<Task> getAllTasks(UUID userId) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getUserId().equals(userId) && !task.isDeleted()) {
                result.add(task);
            }
        }
        return result;
    }

    @Override
    public List<Task> getPendingTasks(UUID userId) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getUserId().equals(userId) && !task.isCompleted() && !task.isDeleted()) {
                result.add(task);
            }
        }
        return result;
    }

    @Override
    public Task addTask(Task task) {
        UUID id = UUID.randomUUID();
        task.setId(id);
        task.setDeleted(false);
        task.setCompleted(false);
        tasks.put(id, task);
        return task;
    }

    @Override
    public void deleteTask(UUID taskId) {
        Task task = tasks.get(taskId);
        if (task != null) {
            task.setDeleted(true);
        }
    }
}
