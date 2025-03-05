package com.project.library_management_system.service.Impl;

import com.project.library_management_system.model.Books;
import com.project.library_management_system.model.BorrowRecord;
import com.project.library_management_system.model.User;
import com.project.library_management_system.repository.BorrowRecordRepository;
import com.project.library_management_system.service.BorrowedBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowedBooksServiceImpl implements BorrowedBooksService {
    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Override
    public List<BorrowRecord> getBorrowedDetails() {
        return borrowRecordRepository.findAll();
    }

    @Override
    public void createBorrowRecord(User user, Books book) {
        BorrowRecord record = new BorrowRecord();
        record.setUser(user);
        record.setBook(book);
        record.setBorrowDate(LocalDate.now());
        record.setDueDate(LocalDate.now().plusDays(15));
        borrowRecordRepository.save(record);
    }

    @Transactional
    @Override
    public void deleteBorrowRecord(User user, Books book) {
        borrowRecordRepository.deleteByUserAndBook(user, book);
    }
}

