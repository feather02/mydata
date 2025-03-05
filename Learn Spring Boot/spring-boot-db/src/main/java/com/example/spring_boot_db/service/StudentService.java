package com.example.spring_boot_db.service;

import com.example.spring_boot_db.model.Student;
import com.example.spring_boot_db.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }


    @Transactional
    public String createStudent(Student student) {
        Student existingStudent = studentRepository.findByNameAndDept(student.getName(), student.getDept());

        if (existingStudent != null) {  // ✅ Instead of Optional
            return "Student already exists!";
        } else {
            studentRepository.save(student);
            return "Student added successfully!";
        }
    }

    public String updateStudent(int id,Student student) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            optionalStudent.get().setName(student.getName());
            optionalStudent.get().setDept(student.getDept());
            return "Student updated successfully";
        }
        else {
            return "Student does not exists!!!";
        }
    }

    public String deleteStudent(int id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            studentRepository.deleteById(id);
            return "Student deleted successfully";
        }
        else {
            return "Student does not exist!";
        }
    }
}
