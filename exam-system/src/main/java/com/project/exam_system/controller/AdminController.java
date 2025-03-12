package com.project.exam_system.controller;

import com.project.exam_system.entity.Admin;
import com.project.exam_system.entity.Faculty;
import com.project.exam_system.service.AdminService;
import com.project.exam_system.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String home() {
        return "index";
    }

    @GetMapping("/admin/login")
    public String adminLoginPage(Model model) {
        model.addAttribute("admin", new Admin());
        return "adminLogin";
    }

    @PostMapping("/admin/login")
    public String adminLogin(Model model, @ModelAttribute("admin") Admin admin) {
        Admin admin1 = adminService.getAdminDetails(admin);
        if (admin1 != null && passwordEncoder.matches(admin.getPassword(), admin1.getPassword())) {
            model.addAttribute("success", "Login Successful");
            return "redirect:/adminDashboard";
        } else {
            model.addAttribute("error", "Incorrect Username or Password!");
            return "adminLogin";
        }
    }

    @GetMapping("/adminDashboard")
    public String adminDashboardPage() {
        return "adminDashboard";
    }

    @GetMapping("/add/faculty")
    public String addFacultyPage(Model model) {
        model.addAttribute("faculty", new Faculty());
        return "addFaculty";
    }

    @PostMapping("/faculty/register")
    public String facultyRegister(Model model, @ModelAttribute("faculty") Faculty faculty) {
        if(facultyService.registerFaculty(faculty)) {
            model.addAttribute("success", "Faculty added successfully!");
            return "addFaculty";
        } else {
            model.addAttribute("error", "Faculty already exists");
            return "addFaculty";
        }
    }

    //    @PostMapping("/admin/register")
//    public String adminRegister(@RequestBody Admin admin) {
//        adminService.registerAdmin(admin);
//        return "Admin Registered successfully";
//    }

}
