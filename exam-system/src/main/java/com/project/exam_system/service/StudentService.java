package com.project.exam_system.service;

import com.project.exam_system.entity.Student;

public interface StudentService {
    void registerStudent(Student student);

    Student findByRollNo(Student student);
}
