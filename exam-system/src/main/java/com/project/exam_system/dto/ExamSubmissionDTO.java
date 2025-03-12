package com.project.exam_system.dto;

import java.util.Map;

public class ExamSubmissionDTO {
    private int examId;
    private Map<Integer, String> answers;

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public Map<Integer, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Integer, String> answers) {
        this.answers = answers;
    }
}

