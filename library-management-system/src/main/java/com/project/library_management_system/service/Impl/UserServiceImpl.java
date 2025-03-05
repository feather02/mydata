package com.project.library_management_system.service.Impl;

import com.project.library_management_system.model.User;
import com.project.library_management_system.repository.UserRepository;
import com.project.library_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.NullCipher;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    //PasswordEncoder is used for hashing the password
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //Cipher is used for data encryption and decryption like messages, data
//    Cipher cipher = new NullCipher();

    @Override
    public void registerUser(User user) {
       // PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword()); // Encrypt Password
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public User getUserDetails(User user) {
        return userRepository.findFirstByUsername(user.getUsername())
                .filter(existingUser -> passwordEncoder.matches(user.getPassword(), existingUser.getPassword()))
                .orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String name) {
        return userRepository.findByUsername(name);
    }


}
