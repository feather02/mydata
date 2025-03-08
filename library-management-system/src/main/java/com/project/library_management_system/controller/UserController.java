package com.project.library_management_system.controller;

import com.project.library_management_system.model.Books;
import com.project.library_management_system.model.User;
import com.project.library_management_system.service.BooksService;
import com.project.library_management_system.service.BorrowedBooksService;
import com.project.library_management_system.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BooksService booksService;

    @Autowired
    private BorrowedBooksService borrowedBooksService;

    @GetMapping("/userRegister")
    public String userRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "userRegister";
    }

    @PostMapping("/userRegister/success")
    public String addNewUser(RedirectAttributes redirectAttributes, @ModelAttribute("user") User user) {
        userService.registerUser(user);
        redirectAttributes.addFlashAttribute("successMessage", "User created successfully!");
        return "redirect:/index";
    }

    @GetMapping("/userLogin")
    public String userLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "userLogin";
    }

    @PostMapping("/userLogin")
    public String userLogin(HttpSession session, Model model, @ModelAttribute("user") User user) {
        User loggedInUser = userService.getUserDetails(user);
        if (loggedInUser != null) {
            session.setAttribute("loggedInUser", loggedInUser); // Store user in session
            model.addAttribute("user", loggedInUser);
            return "redirect:/viewBooks";
        } else {
            model.addAttribute("error", "No such user found!");
            return "userLogin";
        }
    }


    @GetMapping("/viewBooks")
    public String viewBooksPage(Model model) {
        List<Books> allBooks = booksService.getAllBooks();
        if (allBooks == null) {
            allBooks = new ArrayList<>(); // Prevent null errors
        }
        model.addAttribute("availablebooks", allBooks);
        return "viewBooks";
    }


    @Transactional
    @GetMapping("/isAvailable")
    public String toggleBookAvailability(@RequestParam("id") int id, @RequestParam("username") String username) {
        Books book = booksService.getBookById(id);
        User user = userService.getUserByUsername(username); // Get user manually

        if (book.getAvailable()) {
            booksService.updateBookAvailability(id, false);
            borrowedBooksService.createBorrowRecord(user, book);
        } else {
            booksService.updateBookAvailability(id, true);
            borrowedBooksService.deleteBorrowRecord(user, book);
        }
        return "redirect:/viewBooks";
    }
}