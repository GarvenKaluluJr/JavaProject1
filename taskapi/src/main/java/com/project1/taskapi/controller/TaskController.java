package com.project1.taskapi.controller;

import com.project1.taskapi.model.Task;
import com.project1.taskapi.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    public TaskController(TaskService taskService) { this.taskService = taskService; }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable UUID userId) {
        return ResponseEntity.ok(taskService.getAllTasks(userId));
    }

    @GetMapping("/{userId}/pending")
    public ResponseEntity<List<Task>> getPendingTasks(@PathVariable UUID userId) {
        return ResponseEntity.ok(taskService.getPendingTasks(userId));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task created = taskService.addTask(task);
        return ResponseEntity.ok(created);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}
