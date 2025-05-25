package com.project1.taskapi.service.impl;

import com.project1.taskapi.model.User;
import com.project1.taskapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private UserServiceImpl userService;
    private UserRepository mockRepository;

    @BeforeEach
    void setUp() {
        mockRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(mockRepository);
    }

    @Test
    void testRegisterNewUser() {
        User user = new User();
        user.setUsername("garve");
        // Mock: when findByUsername is called, return empty (user does not exist)
        when(mockRepository.findByUsername("garve")).thenReturn(Optional.empty());
        when(mockRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User registered = userService.register(user);
        assertEquals("garve", registered.getUsername());
        verify(mockRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterDuplicateUsernameThrows() {
        User user1 = new User();
        user1.setUsername("testuser");
        // First, registering "testuser" returns empty (doesn't exist)
        when(mockRepository.findByUsername("testuser")).thenReturn(Optional.empty(), Optional.of(user1));
        when(mockRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        userService.register(user1);

        User user2 = new User();
        user2.setUsername("testuser"); // Same username

        // Now findByUsername returns non-empty for duplicate
        Exception exception = assertThrows(RuntimeException.class, () -> userService.register(user2));
        assertTrue(exception.getMessage().toLowerCase().contains("already exists"));
    }

    @Test
    void testLoginSuccess() {
        User user = new User();
        user.setUsername("user123");

        // Mock: return a user for username "user123"
        when(mockRepository.findByUsername("user123")).thenReturn(Optional.of(user));

        User found = userService.login("user123");
        assertNotNull(found);
        assertEquals("user123", found.getUsername());
    }

    @Test
    void testLoginNotFound() {
        // Mock: return empty for unknown username
        when(mockRepository.findByUsername("nonexistent")).thenReturn(Optional.empty());

        User found = userService.login("nonexistent");
        assertNull(found);
    }

    @Test
    void testGetAllUsers() {
        // Mock: return a sample list
        User user1 = new User(); user1.setUsername("a");
        User user2 = new User(); user2.setUsername("b");
        when(mockRepository.findAll()).thenReturn(List.of(user1, user2));

        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
    }
}
