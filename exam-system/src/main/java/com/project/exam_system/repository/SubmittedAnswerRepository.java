package com.project.exam_system.repository;

import com.project.exam_system.entity.SubmittedAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmittedAnswerRepository extends JpaRepository<SubmittedAnswer,Integer> {
    List<SubmittedAnswer> findByExamIdAndRollNo(int examId, String rollNo);
}
