package com.project.exam_system.service.Impl;

import com.project.exam_system.entity.Result;
import com.project.exam_system.repository.ResultRepository;
import com.project.exam_system.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
        @Autowired
        private ResultRepository resultRepository;

        @Override
        @Transactional
        public void saveResult(Result result) {
            resultRepository.save(result);
        }

        @Override
        public Boolean getAlreadyDone(int examId, Integer rollNo) {
                List<Result> result = resultRepository.findByExamIdAndRollNo(examId,rollNo);
                return !result.isEmpty();
        }


        @Override
        @Transactional
        public void publishResult(int examId) {
                List<Result> results = resultRepository.findByExamId(examId);

                if (!results.isEmpty()) {
                        boolean currentStatus = results.get(0).getPublished(); // Check current status
                        boolean newStatus = !currentStatus; // Toggle the status

                        for (Result result : results) {
                                result.setPublished(newStatus);
                        }
                        resultRepository.saveAll(results);
                }
        }

        @Override
        public List<Result> getAllResults() {
                return resultRepository.findAll();
        }

        @Override
        public List<Result> getResultById(String studentRollNo) {
                return resultRepository.findByStudentRollNo(studentRollNo);
        }

        @Override
        public Result getPublishedDetail(int examId) {
                return resultRepository.getPublishedById(examId);
        }
}
