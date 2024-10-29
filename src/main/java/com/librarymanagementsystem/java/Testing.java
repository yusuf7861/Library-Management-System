//package com.librarymanagementsystem.java;
//
//import com.librarymanagementsystem.java.entity.Author;
//import com.librarymanagementsystem.java.entity.Book;
//import com.librarymanagementsystem.java.repository.AuthorRepository;
//import com.librarymanagementsystem.java.repository.BookRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//@Component
//public class Testing implements CommandLineRunner {
//    @Autowired
//    private AuthorRepository authorRepository;
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Check if the database already has data
//        if (authorRepository.count() == 0 && bookRepository.count() == 0) {
//            // Insert Authors
//            Author author1 = new Author();
//            author1.setName("George R.R. Martin");
//            author1.setEmail("grrm@example.com");
//
//            Author author2 = new Author();
//            author2.setName("J.K. Rowling");
//            author2.setEmail("jkrowling@example.com");
//
//            // Save authors
//            authorRepository.saveAll(Arrays.asList(author1, author2));
//
//            // Insert Books
//            Book book1 = new Book();
//            book1.setISBN("978-0553103540");
//            book1.setTitle("A Game of Thrones");
//            book1.setGenre("Fantasy");
//            book1.setAuthor(author1); // Associate book with author1
//
//            Book book2 = new Book();
//            book2.setISBN("978-0553108033");
//            book2.setTitle("A Clash of Kings");
//            book2.setGenre("Fantasy");
//            book2.setAuthor(author1); // Associate book with author1
//
//            Book book3 = new Book();
//            book3.setISBN("978-0747532699");
//            book3.setTitle("Harry Potter and the Philosopher's Stone");
//            book3.setGenre("Fantasy");
//            book3.setAuthor(author2); // Associate book with author2
//
//            Book book4 = new Book();
//            book4.setISBN("978-0747538493");
//            book4.setTitle("Harry Potter and the Chamber of Secrets");
//            book4.setGenre("Fantasy");
//            book4.setAuthor(author2); // Associate book with author2
//
//            // Save books
//            bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4));
//
//            // Print success message
//            System.out.println("Data inserted successfully!");
//        } else {
//            System.out.println("Data already exists in the database.");
//        }
//    }
//}
