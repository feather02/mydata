package com.project.library_management_system.controller;

import com.project.library_management_system.model.Books;
import com.project.library_management_system.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BooksController {

    @Autowired
    private BooksService booksService;

    @GetMapping({"/books","/Books"})
    public String booksPage(Model model) {
        model.addAttribute("availablebooks",booksService.getAllBooks());
        return "books";
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
}
