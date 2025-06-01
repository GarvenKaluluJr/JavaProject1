package com.project1.taskapi.service.impl;

import com.project1.taskapi.model.User;
import com.project1.taskapi.service.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Profile("rabbit")
public class UserServiceRabbitImpl implements UserService {

    private final Map<String, User> usersByUsername = new ConcurrentHashMap<>();

    @Override
    public User register(User user) {
        user.setId(UUID.randomUUID());
        usersByUsername.put(user.getUsername(), user);
        return user;
    }

    @Override
    public User login(String username) {
        return usersByUsername.get(username);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(usersByUsername.values());
    }
}
