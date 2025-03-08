package com.project.exam_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Admin {
    @Id
    private String username;
    private String password;
}
