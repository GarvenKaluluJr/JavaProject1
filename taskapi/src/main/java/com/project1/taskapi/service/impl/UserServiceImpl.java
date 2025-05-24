package com.project1.taskapi.service.impl;

import com.project1.taskapi.model.User;
import com.project1.taskapi.service.UserService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final Map<String, User> usersByUsername = new HashMap<>();
    private final Map<UUID, User> usersById = new HashMap<>();

    @Override
    public User register(User user) {
        if (usersByUsername.containsKey(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        user.setId(UUID.randomUUID());
        usersByUsername.put(user.getUsername(), user);
        usersById.put(user.getId(), user);
        return user;
    }

    @Override
    public User login(String username) {
        return usersByUsername.get(username);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(usersById.values());
    }
}
