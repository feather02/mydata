package com.project.exam_system.repository;

import com.project.exam_system.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    @Query("SELECT r FROM Result r WHERE r.examId = :examId AND r.studentRollNo = :rollNo")
    List<Result> findByExamIdAndRollNo(@Param("examId") int examId, @Param("rollNo") Integer rollNo);

    List<Result> findByExamId(int examId);

    @Query(nativeQuery = true, value = "SELECT * FROM result WHERE student_roll_no =?1 AND published = true")
    List<Result> findByStudentRollNo(String studentRollNo);

    @Query("SELECT r FROM Result r WHERE r.examId = ?1")
    Result getPublishedById(int examId);

}
