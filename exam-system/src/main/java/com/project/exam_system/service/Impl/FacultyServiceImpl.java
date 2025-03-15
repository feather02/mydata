package com.project.exam_system.service.Impl;

import com.project.exam_system.entity.Faculty;
import com.project.exam_system.repository.FacultyRepository;
import com.project.exam_system.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    FacultyRepository facultyRepository;

    @Override
    public Boolean registerFaculty(Faculty faculty) {
        Faculty faculty1 = facultyRepository.findExistByUsername(faculty.getUsername());
        if (faculty1 == null) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            faculty.setPassword(passwordEncoder.encode(faculty.getPassword()));
            facultyRepository.save(faculty);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Faculty loginFaculty(Faculty faculty) {
       return facultyRepository.findExistByUsername(faculty.getUsername());
    }

    @Override
    public Faculty findByUsername(String username) {
        return facultyRepository.findByUsername(username);
    }
}
