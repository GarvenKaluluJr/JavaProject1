package com.project1.taskapi.service.impl;

import com.project1.taskapi.model.Task;
import com.project1.taskapi.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {

    private TaskServiceImpl taskService;
    private TaskRepository taskRepository;
    private UUID userId;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        taskService = new TaskServiceImpl(taskRepository);
        userId = UUID.randomUUID();
    }

    @Test
    void testAddTask() {
        Task task = new Task();
        task.setUserId(userId);
        task.setDescription("Finish assignment");

        // Mock repository to return the same task with id
        Task savedTask = new Task();
        savedTask.setId(UUID.randomUUID());
        savedTask.setUserId(userId);
        savedTask.setDescription("Finish assignment");
        savedTask.setCompleted(false);
        savedTask.setDeleted(false);

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        Task added = taskService.addTask(task);

        assertNotNull(added.getId());
        assertEquals("Finish assignment", added.getDescription());
        assertFalse(added.isCompleted());
        assertFalse(added.isDeleted());
        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void testGetAllTasks() {
        List<Task> tasks = new ArrayList<>();
        Task t1 = new Task();
        t1.setUserId(userId);
        t1.setDescription("Task 1");
        tasks.add(t1);

        Task t2 = new Task();
        t2.setUserId(userId);
        t2.setDescription("Task 2");
        tasks.add(t2);

        when(taskRepository.findByUserIdAndDeletedFalse(userId)).thenReturn(tasks);

        List<Task> all = taskService.getAllTasks(userId);

        assertEquals(2, all.size());
        verify(taskRepository).findByUserIdAndDeletedFalse(userId);
    }

    @Test
    void testGetPendingTasks() {
        List<Task> pendingTasks = new ArrayList<>();
        Task t1 = new Task();
        t1.setUserId(userId);
        t1.setCompleted(false);
        pendingTasks.add(t1);

        when(taskRepository.findByUserIdAndCompletedFalseAndDeletedFalse(userId)).thenReturn(pendingTasks);

        List<Task> pending = taskService.getPendingTasks(userId);

        assertEquals(1, pending.size());
        assertFalse(pending.get(0).isCompleted());
        verify(taskRepository).findByUserIdAndCompletedFalseAndDeletedFalse(userId);
    }

    @Test
    void testDeleteTaskSetsDeletedFlag() {
        Task task = new Task();
        task.setId(UUID.randomUUID());
        task.setUserId(userId);
        task.setDeleted(false);

        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        taskService.deleteTask(task.getId());

        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepository).save(captor.capture());
        assertTrue(captor.getValue().isDeleted());
        verify(taskRepository).findById(task.getId());
    }
}
