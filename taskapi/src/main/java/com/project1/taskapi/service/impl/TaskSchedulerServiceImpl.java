package com.project1.taskapi.service.impl;

import com.project1.taskapi.model.Task;
import com.project1.taskapi.repository.TaskRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Profile("scheduler")
public class TaskSchedulerServiceImpl {

    private final TaskRepository taskRepository;

    public TaskSchedulerServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Scheduled(fixedRate = 60_000)
    @Async
    public void checkOverdueTasks() {
        List<Task> overdueTasks = taskRepository.findByCompletedFalseAndDeletedFalseAndDueDateBefore(LocalDateTime.now());
        for (Task task : overdueTasks) {
            processOverdueTask(task);
        }
    }

    @Async
    public void processOverdueTask(Task task) {
        System.out.println("[Scheduler] Overdue task: " + task.getId() + " - " + task.getDescription());
    }
}
