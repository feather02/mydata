package com.project.exam_system.repository;

import com.project.exam_system.entity.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void findByExamId() {
        List<Question> questionList = questionRepository.findByExamId(1);
        for (Question q: questionList) {
            System.out.println(q);
        }
    }

}