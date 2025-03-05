package com.example.thymeleafdemo.repository;

import com.example.thymeleafdemo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
