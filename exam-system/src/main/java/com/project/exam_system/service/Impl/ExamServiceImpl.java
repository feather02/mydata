package com.project.exam_system.service.Impl;

import com.project.exam_system.entity.Exam;
import com.project.exam_system.repository.ExamRepository;
import com.project.exam_system.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public void createExam(Exam exam) {
        examRepository.save(exam);
    }

    @Override
    public Exam getExamById(int examId) {
        return examRepository.findById(examId).orElse(null);
    }

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public void updateStatus(int id, boolean b) {
        examRepository.findById(id).ifPresent(exam -> exam.setStatus(b));
    }

    @Override
    public List<Exam> getAllExamsByFacultyId(int id) {
        return examRepository.findByFacultyId(id);
    }

    @Override
    @Transactional
    public void deleteExam(int examId) {
        examRepository.deleteByExamId(examId);
    }
}
