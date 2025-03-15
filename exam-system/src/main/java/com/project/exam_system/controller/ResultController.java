package com.project.exam_system.controller;

import com.project.exam_system.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResultController {
    @Autowired
    private ResultService resultService;

    @Transactional
    @GetMapping("/publish")
    public String publishResult(@RequestParam("examId") int examId) {
        resultService.publishResult(examId);
        return "redirect:/facultyDashboard";
    }


}
