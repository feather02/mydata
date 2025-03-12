package com.project.exam_system.service;

import com.project.exam_system.entity.Result;

public interface ResultService {

    void saveResult(Result result);

    Boolean getAlreadyDone(int examId, Integer rollNo);
}
