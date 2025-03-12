package com.project.exam_system.service.Impl;

import com.project.exam_system.entity.Student;
import com.project.exam_system.repository.StudentRepository;
import com.project.exam_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void registerStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        studentRepository.save(student);
    }

    @Override
    public Student findByRollNo(Student student) {
        return studentRepository.findByRollNo(student.getRollNo());
    }
}
