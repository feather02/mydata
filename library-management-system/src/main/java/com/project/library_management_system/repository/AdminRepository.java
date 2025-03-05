package com.project.library_management_system.repository;

import com.project.library_management_system.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,String> {
    Admin findByUsernameAndPassword(String username,String password);
}
