package com.project.library_management_system.controller;

import com.project.library_management_system.model.Books;
import com.project.library_management_system.model.Role;
import com.project.library_management_system.model.User;
import com.project.library_management_system.service.BooksService;
import com.project.library_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BooksService booksService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ User Registration Form
    @GetMapping("/userRegister")
    public String userRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "userRegister";
    }

    // ✅ Handle User Registration
    @PostMapping("/userRegister")
    public String registerUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        user.setRole(Role.ROLE_USER); // Assign default role
        userService.registerUser(user);

        redirectAttributes.addFlashAttribute("successMessage", "User registered successfully! Please log in.");
        return "redirect:/userLogin";
    }

    // ✅ User Login Form
    @GetMapping("/userLogin")
    public String userLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password!");
        }
        return "userLogin";
    }

    // ✅ View Available Books (User Only)
    @GetMapping("/viewBooks")
    public String viewBooksPage(Model model) {
        List<Books> allBooks = booksService.getAllBooks();
        model.addAttribute("availablebooks", allBooks);
        return "viewBooks";
    }

    // ✅ Check Book Availability
    @GetMapping("/isAvailable")
    public String isAvailable(@RequestParam("id") int id) {
        Books book = booksService.getBookById(id);
        boolean check = booksService.isAvailable(book);
        booksService.checkAvailabilty(book, !check);
        return "redirect:/viewBooks";
    }
}
