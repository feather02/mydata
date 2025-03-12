package com.project.exam_system.service;

import com.project.exam_system.entity.Admin;

public interface AdminService {
    void registerAdmin(Admin admin);

    Admin getAdminDetails(Admin admin);
}
