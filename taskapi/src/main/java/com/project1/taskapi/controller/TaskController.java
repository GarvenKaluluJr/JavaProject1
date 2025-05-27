package com.project1.taskapi.controller;

import com.project1.taskapi.model.Task;
import com.project1.taskapi.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{userId}")
    public List<Task> getAllTasks(@PathVariable UUID userId) {
        return taskService.getAllTasks(userId);
    }

    @GetMapping("/pending/{userId}")
    public List<Task> getPendingTasks(@PathVariable UUID userId) {
        return taskService.getPendingTasks(userId);
    }
    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
    }

    @PutMapping("/complete/{taskId}/{userId}")
    public Task markTaskCompleted(@PathVariable UUID taskId, @PathVariable UUID userId) {
        return taskService.markTaskCompleted(taskId, userId);
    }
}
