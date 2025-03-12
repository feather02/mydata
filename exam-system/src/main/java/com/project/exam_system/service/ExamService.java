package com.project.exam_system.service;

import com.project.exam_system.entity.Exam;
import java.util.List;

public interface ExamService {
    void createExam(Exam exam);

    Exam getExamById(int examId);

    List<Exam> getAllExams();

    void updateStatus(int id, boolean b);
}
