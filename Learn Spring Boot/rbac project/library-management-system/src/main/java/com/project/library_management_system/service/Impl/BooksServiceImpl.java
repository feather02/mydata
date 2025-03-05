package com.project.library_management_system.service.Impl;

import com.project.library_management_system.model.Books;
import com.project.library_management_system.repository.BooksRepository;
import com.project.library_management_system.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BooksRepository booksRepository;

    @Override
    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }

    @Override
    public void updateBook(int id, Books books) {
        Optional<Books> existingBook  = booksRepository.findById(id);
            existingBook.get().setBookName(books.getBookName());
            existingBook.get().setBookId(books.getBookId());
            existingBook.get().setAuthor(books.getAuthor());
            booksRepository.save(existingBook.get());
    }

    @Override
    public void deleteBook(int id) {
        booksRepository.deleteById(id);
    }

    @Override
    public Object addBook(Books books) {
        return booksRepository.save(books);
    }

    @Override
    public Books getBookById(int id) {
        return booksRepository.getById(id);
    }

    @Override
    public boolean isAvailable(Books books) {
        return booksRepository.findById(books.getId())
                .map(Books::getAvailable)
                .orElse(false);
    }

    @Override
    public void checkAvailabilty(Books books, boolean b) {
        Optional<Books> availableBook = booksRepository.findById(books.getId());
        availableBook.ifPresentOrElse(book -> {
            book.setAvailable(b);
            booksRepository.save(book);
        }, () -> {
            throw new NoSuchElementException("Book with ID " + books.getId() + " not found!");
        });
    }
}
