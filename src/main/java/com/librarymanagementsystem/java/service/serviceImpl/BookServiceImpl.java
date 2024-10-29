package com.librarymanagementsystem.java.service.serviceImpl;

import com.librarymanagementsystem.java.entity.Author;
import com.librarymanagementsystem.java.entity.Book;
import com.librarymanagementsystem.java.repository.AuthorRepository;
import com.librarymanagementsystem.java.repository.BookRepository;
import com.librarymanagementsystem.java.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Book addBook(Book book, Long authorId) {
        // Fetch the author using authorId
        Optional<Author> authorOptional = authorRepository.findById(authorId);

        if (authorOptional.isPresent()) {
            // Get the author from the optional
            Author author = authorOptional.get();

            // Assign the book to the author
            book.setAuthor(author);

            // Save and return the book
            return bookRepository.save(book);
        } else {
            throw new RuntimeException("Author with id " + authorId + " not found");
        }
    }

//    @Override
//    public void updateBook() {
//
//    }
//
//    @Override
//    public void deleteBook() {
//
//    }

    @Override
    public Book searchBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public List<Book> viewAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author getAuthorByBookId(Long id) {
        Book book = searchBookById(id);
        return book.getAuthor();
    }
}
