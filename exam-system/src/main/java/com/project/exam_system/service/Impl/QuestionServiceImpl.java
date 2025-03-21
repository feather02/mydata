package com.project.exam_system.service.Impl;

import com.project.exam_system.entity.Question;
import com.project.exam_system.repository.QuestionRepository;
import com.project.exam_system.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void addQuestion(Question question) {
        questionRepository.save(question);
    }

    @Override
    public int getNextQuestionNumberForExam(int examId) {
        Integer maxQuestionNumber = questionRepository.findMaxQuestionNumberByExamId(examId);
        return (maxQuestionNumber != null) ? maxQuestionNumber + 1 : 1;  // Start from 1 if no questions exist
    }

    @Override
    public List<Question> getQuestionsByExamId(int examId) {
        return questionRepository.findByExamId(examId);
    }

    @Override
    public void updateQuestion(Question question) {
        int examId=question.getExam().getExamId();
        int qnNo = question.getQuestionNumber();
        Question existingQuestion = questionRepository.findByExam_ExamIdAndQuestionNumber(examId,qnNo);
        if (existingQuestion != null) {
            existingQuestion.setQuestion(question.getQuestion());
            existingQuestion.setOption1(question.getOption1());
            existingQuestion.setOption2(question.getOption2());
            existingQuestion.setOption3(question.getOption3());
            existingQuestion.setOption4(question.getOption4());
            existingQuestion.setAnswer(question.getAnswer());
        }
        questionRepository.save(existingQuestion);
    }

}
