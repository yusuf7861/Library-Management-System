package com.librarymanagementsystem.java.service;

import java.util.List;

import com.librarymanagementsystem.java.entity.Address;
import com.librarymanagementsystem.java.entity.Author;
import com.librarymanagementsystem.java.entity.Book;


public interface AuthorService {
    public void addAuthor(Author author);
//    public Author updateAuthor(Long id);
//    public void deleteAuthor();
    public Author searchAuthorById(Long id);
    public List<Author> viewAllAuthors();
    public List<Book> getBooksByAuthor(Long id);
    public Address getAuthorAddress(Long authorId);
}
