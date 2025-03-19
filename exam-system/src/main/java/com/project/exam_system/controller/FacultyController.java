package com.project.exam_system.controller;

import com.project.exam_system.entity.Exam;
import com.project.exam_system.entity.Faculty;
import com.project.exam_system.entity.Result;
import com.project.exam_system.service.ExamService;
import com.project.exam_system.service.FacultyService;
import com.project.exam_system.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class FacultyController {

    @Autowired
    FacultyService facultyService;

    @Autowired
    ExamService examService;

    @Autowired
    ResultService resultService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/faculty/login")
    public String facultyLoginPage(Model model) {
        model.addAttribute("faculty", new Faculty());
        return "facultyLogin";
    }

    @PostMapping("/faculty/login")
    public String loginFaculty(@ModelAttribute("faculty") Faculty faculty, HttpSession session, Model model) {
        Faculty existingFaculty = facultyService.findByUsername(faculty.getUsername());

        if (existingFaculty != null && passwordEncoder.matches(faculty.getPassword(), existingFaculty.getPassword())) {
            session.setAttribute("loggedFaculty", existingFaculty); // Store in session
            return "redirect:/facultyDashboard";
        } else {
            model.addAttribute("error", "Incorrect Username or Password!");
            return "facultyLogin";
        }
    }

//    @GetMapping("/facultyDashboard")
//    public String facultyDashboardPage(Model model) {
//        List<Exam> examList = examService.getAllExams();
//        List<Result> results = resultService.getAllResults();
//
//        model.addAttribute("result",results);
//        model.addAttribute("exams",examList);
//        return "facultyDashboard";
//    }

    @GetMapping("/facultyDashboard")
    public String facultyDashboardPage(HttpSession session, Model model) {
        Faculty faculty = (Faculty) session.getAttribute("loggedFaculty");
        if (faculty == null) {
            return "redirect:/faculty/login";
        }

        List<Exam> examList = examService.getAllExams();

        // Map examId -> List<Result> (to handle multiple students per exam)
        Map<Integer, List<Result>> resultMap = resultService.getAllResults()
                .stream()
                .collect(Collectors.groupingBy(Result::getExamId));

        model.addAttribute("exams", examList);
        model.addAttribute("resultMap", resultMap); // Now holds lists of results
        return "facultyDashboard";
    }




    @GetMapping("/faculty/logout")
    public String logoutFaculty(HttpSession session) {
        session.invalidate(); // Clear session
        return "redirect:/" +
                "";
    }
}
