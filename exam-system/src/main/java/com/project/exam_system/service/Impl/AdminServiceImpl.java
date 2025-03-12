package com.project.exam_system.service.Impl;

import com.project.exam_system.entity.Admin;
import com.project.exam_system.repository.AdminRepository;
import com.project.exam_system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void registerAdmin(Admin admin) {
        String encodedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodedPassword);
        adminRepository.save(admin);
    }

    @Override
    public Admin getAdminDetails(Admin admin) {
        return adminRepository.findFirstByUsername(admin.getUsername())
                .filter(existingDetails -> passwordEncoder.matches(admin.getPassword(), existingDetails.getPassword()))
                .orElse(null);
    }
}
