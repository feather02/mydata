package com.project.exam_system.repository;

import com.project.exam_system.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    @Query("SELECT MAX(q.questionNumber) FROM Question q WHERE q.exam.examId = :examId")
    Integer findMaxQuestionNumberByExamId(@Param("examId") int examId);

    @Query("SELECT q FROM Question q WHERE q.exam.examId = :examId")
    List<Question> findByExamId(int examId);

    Question findByExam_ExamIdAndQuestionNumber(int examId, int qnNo);
}
