package com.project.library_management_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String bookName;

    @Column(nullable = false, unique = true)
    private String bookId;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private boolean available = true;  // Default: available

    public boolean getAvailable() {
        return available;
    }

}

