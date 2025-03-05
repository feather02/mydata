package com.project.library_management_system.repository;

import com.project.library_management_system.model.Books;
import com.project.library_management_system.model.BorrowRecord;
import com.project.library_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord,Integer> {
    void deleteByUserAndBook(User user, Books book);
}
