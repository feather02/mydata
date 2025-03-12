package com.project.exam_system.dto;

import com.project.exam_system.entity.Question;
import java.util.List;

public class QuestionForm {
    private List<Question> questionList;

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
