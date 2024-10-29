package com.librarymanagementsystem.java.service.serviceImpl;

import com.librarymanagementsystem.java.entity.Address;
import com.librarymanagementsystem.java.entity.Author;
import com.librarymanagementsystem.java.entity.Book;
import com.librarymanagementsystem.java.repository.AuthorRepository;
import com.librarymanagementsystem.java.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("authorService")
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

//    @Override
//    public Author updateAuthor(Long id) {
//        Author author2 = new Author();
//        author2.setEmail("");
//        return null;
//    }

//    @Override
//    public void deleteAuthor() {
//
//    }

    @Override
    public Author searchAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
    }

    @Override
    public List<Author> viewAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public List<Book> getBooksByAuthor(Long id) {
        Author author = searchAuthorById(id);
        return author.getBooks();
    }

    @Override
    public Address getAuthorAddress(Long id) {
        Author author = searchAuthorById(id);
        return author.getAddress();
    }
}
