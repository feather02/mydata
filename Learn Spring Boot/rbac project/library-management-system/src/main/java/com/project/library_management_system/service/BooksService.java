package com.project.library_management_system.service;

import com.project.library_management_system.model.Books;

import java.util.List;

public interface BooksService {
    List<Books> getAllBooks();

    void updateBook(int id, Books books);

    void deleteBook(int id);

    Object addBook(Books books);

    Books getBookById(int id);

    boolean isAvailable(Books books);

    void checkAvailabilty(Books books, boolean b);
}
