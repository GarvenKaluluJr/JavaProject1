package com.project1.taskapi.service.impl;

import com.project1.taskapi.model.Task;
import com.project1.taskapi.repository.TaskRepository;
import com.project1.taskapi.service.TaskService;
import com.project1.taskapi.messaging.TaskPublisher;
import com.project1.taskapi.messaging.TaskMessage;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Profile({"postgres"})
public class TaskServiceDbImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskPublisher taskPublisher;

    // Note: Add TaskPublisher to the constructor
    public TaskServiceDbImpl(TaskRepository taskRepository, TaskPublisher taskPublisher) {
        this.taskRepository = taskRepository;
        this.taskPublisher = taskPublisher;
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
        Task saved = taskRepository.save(task);

        // Send a message to RabbitMQ when a task is created
        TaskMessage msg = new TaskMessage();
        msg.setTaskId(saved.getId());
        msg.setUserId(saved.getUserId());
        msg.setDescription(saved.getDescription());
        taskPublisher.sendTask(msg);

        return saved;
    }

    @Override
    public void deleteTask(UUID taskId) {
        taskRepository.findById(taskId).ifPresent(task -> {
            task.setDeleted(true);
            taskRepository.save(task);
        });
    }
}
