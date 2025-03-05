package com.project.library_management_system.service;

import com.project.library_management_system.model.Books;

import java.util.List;

public interface BooksService {
    List<Books> getAllBooks();

    void updateBook(int id, Books books);

    void deleteBook(int id);

    void addBook(Books books);

    Books getBookById(int id);

    boolean isAvailable(Books books);

    void checkAvailability(Books books, boolean b);

    void updateBookAvailability(int id, boolean status);
}
