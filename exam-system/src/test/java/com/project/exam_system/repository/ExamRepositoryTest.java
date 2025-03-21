package com.project.exam_system.repository;

import com.project.exam_system.entity.Exam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExamRepositoryTest {

    @Autowired
    private ExamRepository examRepository;

    @Test
    @Transactional
    public void deleteExamById() {
        examRepository.deleteByExamId(3);
    }
}