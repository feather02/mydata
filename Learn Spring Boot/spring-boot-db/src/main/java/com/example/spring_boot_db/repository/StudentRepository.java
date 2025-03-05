package com.example.spring_boot_db.repository;

import com.example.spring_boot_db.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByNameAndDept(String name, String dept);
}
