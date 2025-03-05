package com.project.library_management_system.controller;

import com.project.library_management_system.model.Admin;
import com.project.library_management_system.service.AdminService;
import com.project.library_management_system.service.BooksService;
import com.project.library_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private BooksService booksService;

    // Home Page
    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/index")
    public String showIndexPage() {
        return "index"; // Maps to src/main/resources/templates/index.html
    }

    // Admin Login Page
    @GetMapping("/adminlogin")
    public String adminLoginPage(Model model) {
        model.addAttribute("admin", new Admin());
        return "adminlogin";
    }

    @PostMapping("/adminlogin")
    public String processAdminLogin(Model model, @ModelAttribute("admin") Admin admin) {
        boolean isValid = adminService.getAdminDetails(admin);
        if (isValid) {
            return "redirect:/adminDashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "adminlogin";
        }
    }
}
