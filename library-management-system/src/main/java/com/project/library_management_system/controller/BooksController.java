package com.project.library_management_system.controller;

import com.project.library_management_system.model.Books;
import com.project.library_management_system.model.BorrowRecord;
import com.project.library_management_system.service.BooksService;
import com.project.library_management_system.service.BorrowedBooksService;
import com.project.library_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BooksController {

    @Autowired
    private BooksService booksService;

    @Autowired
    private UserService userService;

    @Autowired
    private BorrowedBooksService borrowedBooksService;

    @GetMapping("/adminDashboard")
    public String adminPage(Model model) {
        model.addAttribute("availablebooks",booksService.getAllBooks());
        return "adminDashboard";
    }

    @GetMapping("/books")
    public String booksPage(Model model) {
        model.addAttribute("availablebooks", booksService.getAllBooks());
        return "books"; // This should return books.html
    }


    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        booksService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/addbook")
    public String addBookForm(Model model) {
        model.addAttribute("newbook", new Books()); // Initialize an empty book object
        return "addbook"; // Returns the HTML page
    }

    @PostMapping("/books/add")
    public String saveBook(@ModelAttribute("newbook") Books books) {
        booksService.addBook(books); // Saves the book
        return "redirect:/books"; // Redirects to book list
    }

    @GetMapping("/update/{id}")
    public String updateBookForm(@PathVariable("id") int id, Model model) {
        Books existingBook = booksService.getBookById(id); // Fetch book from DB
        model.addAttribute("updatebook", existingBook);
        return "updatebook";
    }

    @PostMapping("/updatebook/{id}")
    public String updateBook(@PathVariable("id") int id, @ModelAttribute("updatebook") Books books) {
        booksService.updateBook(id, books);
        return "redirect:/books";
    }

    @GetMapping("/books/toggleAvailability/{id}")
    public String toggleBookAvailability(@PathVariable int id) {
        Books book = booksService.getBookById(id);
        booksService.updateBookAvailability(id, !book.getAvailable());
        return "redirect:/books";
    }

    @GetMapping("/availBooks")
    public String availBooks(Model model) {
        model.addAttribute("availablebooks",booksService.getAllBooks());
        return "books";
    }

    @GetMapping("/registeredUsers")
    public String getRegisteredUsers(Model model) {
        model.addAttribute("registeredUsers",userService.getAllUsers());
        return "registeredUsers";
    }

    @GetMapping("/borrowedBooks")
    public String getBorrowedBooksDetails(Model model) {
        List<BorrowRecord> borrowedBooks = borrowedBooksService.getBorrowedDetails();
        if (borrowedBooks == null) {
            borrowedBooks = new ArrayList<>(); // Prevent null issues
        }
        model.addAttribute("borrowedBooks", borrowedBooks);
        return "borrowedBooks";
    }

}
