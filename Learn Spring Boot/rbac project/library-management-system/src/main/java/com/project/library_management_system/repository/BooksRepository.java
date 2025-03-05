package com.project.library_management_system.repository;

import com.project.library_management_system.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {
    boolean existsByIdAndAvailable(int id, Boolean available);
}

