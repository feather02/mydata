package com.project.exam_system.repository;

import com.project.exam_system.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Integer> {
    List<Exam> findByFacultyId(int id);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM exam WHERE exam_id=?1")
    void deleteByExamId(int examId);
}
