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
}
