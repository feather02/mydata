package com.project.exam_system.controller;

import com.project.exam_system.entity.Exam;
import com.project.exam_system.entity.Faculty;
import com.project.exam_system.service.ExamService;
import com.project.exam_system.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
public class FacultyController {

    @Autowired
    FacultyService facultyService;

    @Autowired
    ExamService examService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/faculty/login")
    public String facultyLoginPage(Model model) {
        model.addAttribute("faculty", new Faculty());
        return "facultyLogin";
    }

    @PostMapping("/faculty/login")
    public String loginFaculty(Model model, @ModelAttribute("faculty") Faculty faculty, HttpSession session) {
        Faculty existingFaculty = facultyService.loginFaculty(faculty);
        if (existingFaculty != null && passwordEncoder.matches(faculty.getPassword(), existingFaculty.getPassword())) {
            session.setAttribute("loggedFaculty", existingFaculty); // Store in session
            return "redirect:/facultyDashboard";
        } else {
            model.addAttribute("error", "Incorrect Username or Password!");
            return "facultyLogin";
        }
    }

    @GetMapping("/facultyDashboard")
    public String facultyDashboardPage(Model model) {
        List<Exam> examList = examService.getAllExams();
        model.addAttribute("exams",examList);
        return "facultyDashboard";
    }

    @GetMapping("/faculty/logout")
    public String logoutFaculty(HttpSession session) {
        session.invalidate(); // Clear session
        return "redirect:/faculty/login";
    }
}
