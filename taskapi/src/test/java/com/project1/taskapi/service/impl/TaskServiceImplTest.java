package com.project1.taskapi.service.impl;

// ...other imports...
import com.project1.taskapi.model.Task;
import com.project1.taskapi.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.test.util.ReflectionTestUtils;

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
        CacheManager cacheManager = new ConcurrentMapCacheManager("tasks", "pendingTasks");
        taskService = new TaskServiceImpl(taskRepository);
        userId = UUID.randomUUID();
    }

    @Test
    void testAddTask() {
        Task task = new Task();
        task.setUserId(userId);
        task.setDescription("Finish assignment");
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
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    // ... (other tests)
}
