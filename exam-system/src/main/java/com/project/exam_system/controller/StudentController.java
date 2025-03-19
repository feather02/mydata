package com.project.exam_system.controller;

import com.project.exam_system.entity.*;
import com.project.exam_system.repository.SubmittedAnswerRepository;
import com.project.exam_system.service.ExamService;
import com.project.exam_system.service.QuestionService;
import com.project.exam_system.service.ResultService;
import com.project.exam_system.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    ExamService examService;

    @Autowired
    private ResultService resultService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private SubmittedAnswerRepository submittedAnswerRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/student/register")
    public String studentRegistrationPage(Model model) {
        model.addAttribute("student", new Student());
        return "studentRegister";
    }

    @PostMapping("/student/register")
    public String registerStudent(Model model,@ModelAttribute("student") Student student) {
        Student student1 = studentService.findByRollNo(student);
        if (student1 == null) {
            studentService.registerStudent(student);
            model.addAttribute("success", "Student registered successfully!");
            return "studentRegisterSuccessful";
        } else {
            model.addAttribute("error", "Student already exists");
            return "studentRegister";
        }
    }

    @GetMapping("/student/login")
    public String studentLoginPage(Model model) {
        model.addAttribute("student", new Student());
        return "studentLogin";
    }

    @PostMapping("/student/login")
    public String loginStudent(HttpSession session, Model model, @ModelAttribute("student") Student student) {
        Student student1 = studentService.findByRollNo(student);

        if (student1 != null && passwordEncoder.matches(student.getPassword(), student1.getPassword())) {
            session.setAttribute("loggedStudent", student1); // Store student in session
            return "redirect:/studentDashboard"; // Redirect to ensure the session is used
        } else {
            model.addAttribute("error", "Incorrect Username or Password!");
            return "studentLogin";
        }
    }

    @GetMapping("/studentDashboard")
    public String studentDashboardPage(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("loggedStudent");

        if (student == null) {
            return "redirect:/student/login"; // Redirect if not logged in
        }

//        System.out.println(student.getRollNo());

        List<Result> getResult = resultService.getResultById(String.valueOf(student.getRollNo()));
        model.addAttribute("result", getResult);
//        System.out.println(getResult);

        List<Exam> getAllExams = examService.getAllExams();
        model.addAttribute("exams", getAllExams);
        model.addAttribute("student", student);
        return "studentDashboard";
    }

    @GetMapping("/student/logout")
    public String studentLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";

    }

    @GetMapping("/viewAnswers")
    public String viewAnswers(@RequestParam("id") int examId, @RequestParam("rollNo") String rollNo, Model model) {
        Result result = resultService.getResultByRollNoAndExamId(examId,rollNo);
        List<SubmittedAnswer> submittedAnswer = submittedAnswerRepository.findByExamIdAndRollNo(examId,rollNo);
        if (result != null) {
            List<Question> questionList = questionService.getQuestionsByExamId(examId);
            model.addAttribute("result",result);
            model.addAttribute("submittedAnswers",submittedAnswer);
            model.addAttribute("questions", questionList);
        }
        return "viewAnswers";
    }
}
