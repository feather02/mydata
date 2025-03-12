package com.project.exam_system.repository;

import com.project.exam_system.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByRollNo(int rollNo);
}
