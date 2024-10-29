package com.librarymanagementsystem.java.controller;

import com.librarymanagementsystem.java.entity.Book;
import com.librarymanagementsystem.java.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    // Changed: Updated the mapping to be more RESTful
    @PostMapping("/add")
    public ResponseEntity<?> addBook(@ModelAttribute Book book,
                                     @RequestParam Long authorId,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Collect error messages for validation failures
            String errorMessages = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body("Invalid book details: " + errorMessages);
        }

        try {
            // Add the book using the service
            Book addedBook = bookService.addBook(book, authorId);
            return ResponseEntity.ok(addedBook);  // Return the added book object in JSON format
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding book: " + e.getMessage());
        }
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        try {
            Book book = bookService.searchBookById(id);
            if (book == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found!");
            }

            return ResponseEntity.ok(book);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while trying to get book with id: " + id);
        }

//        Book book = bookService.searchBookById(id);
//        if(book == null)
//        {
//            model.addAttribute("book", "Book not found!");
//        }
//        return "index";
    }

    // Changed: Updated to return a view name instead of a List<Book>
    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks(Model model)
    {
        try
        {
            if(bookService.viewAllBooks().isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No books found");
            }
            return ResponseEntity.ok(bookService.viewAllBooks());
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while trying to get all books: " + e.getMessage());

        }
//        List<Book> books = bookService.viewAllBooks();
//        model.addAttribute("books", books);
//        return "index";
    }
}
