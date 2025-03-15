package com.project.exam_system.service;

import com.project.exam_system.entity.Result;

import java.util.List;

public interface ResultService {

    void saveResult(Result result);

    Boolean getAlreadyDone(int examId, Integer rollNo);

    void publishResult(int examId);

    List<Result> getAllResults();

    List<Result> getResultById(String studentRollNo);

    Result getPublishedDetail(int examId);
}
