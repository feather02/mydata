package com.project.exam_system.service;

import com.project.exam_system.entity.Question;

import java.util.List;

public interface QuestionService {
    void addQuestion(Question question);

    int getNextQuestionNumberForExam(int examId);

    List<Question> getQuestionsByExamId(int examId);

    void updateQuestion(Question question);
}
