package com.librarymanagementsystem.java.repository;

import com.librarymanagementsystem.java.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    public Book findByTitle(String title);
    public Book findByAuthorName(String authorName);
}
