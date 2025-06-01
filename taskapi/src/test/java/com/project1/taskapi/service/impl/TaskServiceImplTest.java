package com.project1.taskapi.service.impl;

import com.project1.taskapi.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceImplTest {

    private TaskServiceImpl taskService;
    private UUID userId;

    @BeforeEach
    void setUp() {
        taskService = new TaskServiceImpl();
        userId = UUID.randomUUID();
    }

    @Test
    void testAddTask() {
        Task task = new Task();
        task.setUserId(userId);
        task.setDescription("Finish assignment");
        Task added = taskService.addTask(task);

        assertNotNull(added.getId());
        assertEquals("Finish assignment", added.getDescription());
        assertFalse(added.isCompleted());
        assertFalse(added.isDeleted());
    }

    @Test
    void testGetAllTasks() {
        Task t1 = new Task();
        t1.setUserId(userId);
        t1.setDescription("Task 1");
        taskService.addTask(t1);

        Task t2 = new Task();
        t2.setUserId(userId);
        t2.setDescription("Task 2");
        taskService.addTask(t2);

        List<Task> all = taskService.getAllTasks(userId);
        assertEquals(2, all.size());
    }

    @Test
    void testGetPendingTasks() {
        Task t1 = new Task();
        t1.setUserId(userId);
        t1.setCompleted(false);
        taskService.addTask(t1);

        Task t2 = new Task();
        t2.setUserId(userId);
        t2.setCompleted(true);
        taskService.addTask(t2);

        List<Task> pending = taskService.getPendingTasks(userId);
        assertEquals(1, pending.size());
        assertFalse(pending.get(0).isCompleted());
    }

    @Test
    void testDeleteTaskSetsDeletedFlag() {
        Task task = new Task();
        task.setUserId(userId);
        Task added = taskService.addTask(task);

        taskService.deleteTask(added.getId());

        List<Task> all = taskService.getAllTasks(userId);
        assertEquals(0, all.size(), "Deleted tasks should not show up in getAllTasks");
    }
}
