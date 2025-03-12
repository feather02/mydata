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
@Table(name = "result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int examId;
    private String examName;
    private String studentName;
    private String studentRollNo;
    private int noOfQuestions;
    private int marks;

    public Result(int examId, String examName, String studentName, Integer rollNo, int totalQuestions, int score) {
        this.examId = examId;
        this.examName = examName;
        this.studentName = studentName;
        this.studentRollNo = rollNo.toString();
        this.noOfQuestions = totalQuestions;
        this.marks = score;
    }
}
