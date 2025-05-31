package com.project1.taskapi.service.impl;

import com.project1.taskapi.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
    }

    @Test
    void testRegisterNewUser() {
        User user = new User();
        user.setUsername("garve");
        User registered = userService.register(user);

        assertNotNull(registered.getId());
        assertEquals("garve", registered.getUsername());
    }

    @Test
    void testRegisterDuplicateUsernameThrows() {
        User user1 = new User();
        user1.setUsername("testuser");
        userService.register(user1);

        User user2 = new User();
        user2.setUsername("testuser"); // Same username

        Exception exception = assertThrows(RuntimeException.class, () -> userService.register(user2));
        assertTrue(exception.getMessage().toLowerCase().contains("already exists"));
    }

    @Test
    void testLoginSuccess() {
        User user = new User();
        user.setUsername("user123");
        userService.register(user);

        User found = userService.login("user123");
        assertNotNull(found);
        assertEquals("user123", found.getUsername());
    }

    @Test
    void testLoginNotFound() {
        User found = userService.login("nonexistent");
        assertNull(found);
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User();
        user1.setUsername("a");
        userService.register(user1);

        User user2 = new User();
        user2.setUsername("b");
        userService.register(user2);

        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
    }
}
