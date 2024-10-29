package com.librarymanagementsystem.java.service;

import java.util.List;

import com.librarymanagementsystem.java.entity.Author;
import com.librarymanagementsystem.java.entity.Book;


public interface BookService {
    public Book addBook(Book book, Long authorId);
//    public void updateBook();
//    public void deleteBook();
    public Book searchBookById(Long id);
    public List<Book> viewAllBooks();
    public Author getAuthorByBookId(Long id);

}
