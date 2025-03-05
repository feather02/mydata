package com.project.library_management_system.service.Impl;

import com.project.library_management_system.model.Admin;
import com.project.library_management_system.repository.AdminRepository;
import com.project.library_management_system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public boolean getAdminDetails(Admin admin) {
        Admin admin1 = adminRepository.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
        return admin1!=null;
    }
}
