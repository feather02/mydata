package com.project.library_management_system.service;

import com.project.library_management_system.model.Books;
import com.project.library_management_system.model.BorrowRecord;
import com.project.library_management_system.model.User;

import java.util.List;

public interface BorrowedBooksService {
    List<BorrowRecord> getBorrowedDetails();

    void createBorrowRecord(User user, Books book);
    void deleteBorrowRecord(User user, Books book);

}
