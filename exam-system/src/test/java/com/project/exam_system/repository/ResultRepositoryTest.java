package com.project.exam_system.repository;

import com.project.exam_system.entity.Result;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResultRepositoryTest {
    @Autowired
    ResultRepository resultRepository;

    @Test
    public void testFindByExamId() {
        List<Result> resultList = resultRepository.findByExamId(14);
        System.out.println(resultList);
    }

    @Test
    public void findByStudentRollNo() {
        List<Result> res = resultRepository.findByStudentRollNo("1111");
        System.out.println(res);
    }

    @Test
    public void getResultByRollNoAndExamId() {
        Result result = resultRepository.findByStudentRollNoAndExamId("1001",1);
        System.out.println(result);
    }
}