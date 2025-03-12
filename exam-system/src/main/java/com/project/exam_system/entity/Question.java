package com.project.exam_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    @ManyToOne
    @JoinColumn(name = "examId", referencedColumnName = "examId", nullable = false)
    private Exam exam;  // Reference to Exam entity, but only exam_id will be stored

    private int questionNumber;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int answer;
}

