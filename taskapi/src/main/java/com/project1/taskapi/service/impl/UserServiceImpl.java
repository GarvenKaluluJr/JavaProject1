package com.project1.taskapi.service.impl;

import com.project1.taskapi.model.User;
import com.project1.taskapi.repository.UserRepository;
import com.project1.taskapi.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        userRepository.findByUsername(user.getUsername())
                .ifPresent(u -> { throw new RuntimeException("Username already exists"); });
        return userRepository.save(user);
    }

    @Override
    public User login(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
