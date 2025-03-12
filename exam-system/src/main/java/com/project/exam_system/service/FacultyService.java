package com.project.exam_system.service;

import com.project.exam_system.entity.Faculty;

public interface FacultyService {
    Boolean registerFaculty(Faculty faculty);

    Faculty loginFaculty(Faculty faculty);
}
