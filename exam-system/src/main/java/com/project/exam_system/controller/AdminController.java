package com.project.exam_system.controller;

import com.project.exam_system.entity.Admin;
import com.project.exam_system.entity.Faculty;
import com.project.exam_system.service.AdminService;
import com.project.exam_system.service.FacultyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private FacultyService facultyService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/")
    public String home(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @GetMapping("/admin/login")
    public String adminLoginPage(Model model) {
        model.addAttribute("admin", new Admin());
        return "adminLogin";
    }

    @PostMapping("/admin/login")
    public String adminLogin(HttpSession session, Model model, @ModelAttribute("admin") Admin admin) {
        Admin admin1 = adminService.getAdminDetails(admin);
        if (admin1 != null && passwordEncoder.matches(admin.getPassword(), admin1.getPassword())) {
            session.setAttribute("loggedAdmin",admin);
            model.addAttribute("success", "Login Successful");
            return "redirect:/adminDashboard";
        } else {
            model.addAttribute("error", "Incorrect Username or Password!");
            return "adminLogin";
        }
    }

    @GetMapping("/adminDashboard")
    public String adminDashboardPage(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("loggedAdmin");
        session.removeAttribute("loggedFaculty");
        if (admin ==null) {
            return "redirect:/admin/login";
        }

        return "adminDashboard";
    }

    @GetMapping("/add/faculty")
    public String addFacultyPage(HttpSession session,Model model) {
        Admin admin = (Admin) session.getAttribute("loggedAdmin");

        if (admin == null) {
            return "redirect:/admin/login";
        }

        model.addAttribute("faculty", new Faculty());
        return "addFaculty";
    }

    @PostMapping("/faculty/register")
    public String facultyRegister(HttpSession session,Model model, @ModelAttribute("faculty") Faculty faculty) {
        Admin admin = (Admin) session.getAttribute("loggedAdmin");

        if (admin == null) {
            return "redirect:/admin/login";
        }

        if(facultyService.registerFaculty(faculty)) {
            model.addAttribute("success", "Faculty added successfully!");
            return "addFacultySuccessful";
        } else {
            model.addAttribute("error", "Faculty already exists");
            return "addFaculty";
        }
    }

    @GetMapping("/goToAdminDashboard")
    public String goToAdminDashboard() {
        return "redirect:/adminDashboard";
    }

    @GetMapping("/admin/logout")
    public String adminLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    //    @PostMapping("/admin/register")
//    public String adminRegister(@RequestBody Admin admin) {
//        adminService.registerAdmin(admin);
//        return "Admin Registered successfully";
//    }
}
