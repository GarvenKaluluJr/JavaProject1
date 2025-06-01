package com.project1.taskapi.service.impl;

import com.project1.taskapi.model.User;
import com.project1.taskapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
@ActiveProfiles("postgres")
class UserServiceDbImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceDbImpl userService;

    @Test
    void testRegisterUser() {
        User user = new User(UUID.randomUUID(), "testuser", "testpass");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User saved = userService.register(user);
        assertEquals("testuser", saved.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }
}
