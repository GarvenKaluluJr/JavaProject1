package com.project1.taskapi.service;

import com.project1.taskapi.model.User;
import java.util.UUID;
import java.util.List;

public interface UserService {
    User register(User user);
    User login(String username);
    List<User> getAllUsers();
}
