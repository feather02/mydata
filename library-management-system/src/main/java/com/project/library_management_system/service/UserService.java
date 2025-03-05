package com.project.library_management_system.service;

import com.project.library_management_system.model.User;

import java.util.List;

public interface UserService {
    void registerUser(User user);

    User getUserDetails(User user);

    List<User> getAllUsers();

    User getUserByUsername(String name);
}
