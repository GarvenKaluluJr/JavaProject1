package com.project1.taskapi.service.impl;

import com.project1.taskapi.model.Task;
import com.project1.taskapi.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
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
        // Simulate repository.save() returning the same task with an ID
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

        // Ensure repository.save was called
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void testGetAllTasks() {
        Task t1 = new Task();
        t1.setUserId(userId);
        t1.setDescription("Task 1");

        Task t2 = new Task();
        t2.setUserId(userId);
        t2.setDescription("Task 2");

        when(taskRepository.findByUserIdAndDeletedFalse(userId)).thenReturn(Arrays.asList(t1, t2));

        List<Task> all = taskService.getAllTasks(userId);
        assertEquals(2, all.size());
        assertEquals("Task 1", all.get(0).getDescription());
        assertEquals("Task 2", all.get(1).getDescription());
        verify(taskRepository, times(1)).findByUserIdAndDeletedFalse(userId);
    }

    @Test
    void testGetPendingTasks() {
        Task t1 = new Task();
        t1.setUserId(userId);
        t1.setCompleted(false);

        when(taskRepository.findByUserIdAndCompletedFalseAndDeletedFalse(userId)).thenReturn(List.of(t1));

        List<Task> pending = taskService.getPendingTasks(userId);
        assertEquals(1, pending.size());
        assertFalse(pending.get(0).isCompleted());
        verify(taskRepository, times(1)).findByUserIdAndCompletedFalseAndDeletedFalse(userId);
    }

    @Test
    void testDeleteTaskSetsDeletedFlag() {
        UUID taskId = UUID.randomUUID();
        Task task = new Task();
        task.setId(taskId);
        task.setUserId(userId);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        taskService.deleteTask(taskId);

        // Capture the task that was saved
        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepository).save(captor.capture());
        Task deletedTask = captor.getValue();
        assertTrue(deletedTask.isDeleted());
    }
}
