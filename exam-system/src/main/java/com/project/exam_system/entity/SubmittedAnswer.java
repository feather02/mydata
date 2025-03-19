package com.project.exam_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubmittedAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String rollNo;
    private int examId;
    private int questionNumber;
    private int correctAnswer;
    private int submittedAnswer;

    @ManyToOne
    @JoinColumn(name = "questionId", referencedColumnName = "questionId")
    private Question question;

    public SubmittedAnswer(String rollNo, int examId, int questionNumber, int correctAnswer, int submittedAnswer, Question question) {
        this.rollNo = rollNo;
        this.examId = examId;
        this.questionNumber = questionNumber;
        this.correctAnswer = correctAnswer;
        this.submittedAnswer = submittedAnswer;
        this.question = question;
    }
}
