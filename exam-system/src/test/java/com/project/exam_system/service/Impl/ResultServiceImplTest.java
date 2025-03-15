package com.project.exam_system.service.Impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ResultServiceImplTest {
    @Autowired
    ResultServiceImpl rs;

    @Test
    public void test() {
        System.out.println(rs.getAllResults());
    }

}