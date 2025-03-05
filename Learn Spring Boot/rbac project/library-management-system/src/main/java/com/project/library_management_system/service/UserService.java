package com.project.library_management_system.service;

import com.project.library_management_system.model.User;

public interface UserService {
    void registerUser(User user);

    User getUserDetails(User user);
}
