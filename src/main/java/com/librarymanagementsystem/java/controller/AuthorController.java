package com.librarymanagementsystem.java.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.librarymanagementsystem.java.entity.Author;
import com.librarymanagementsystem.java.service.AuthorService;

@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @GetMapping("/addAuthorForm")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "index"; // Ensure this is the name of your form template
    }


    @PostMapping("/add")
    public ResponseEntity<?> addAuthor(@RequestBody Author author, BindingResult result)
    {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Author data");
        }

        authorService.addAuthor(author);
        return ResponseEntity.ok(author);


//        if (result.hasErrors()) {
//            return "index";
//        }
//
//        authorService.addAuthor(author);
//        model.addAttribute("author", author);
//        // Changed: Redirect to the home page instead of returning "index"
//        return "redirect:/";
    }

    @GetMapping("/getauthor/{id}")
    public ResponseEntity<?> getAuthor(@PathVariable Long id, Model model)
    {
        try {
            Author author = authorService.searchAuthorById(id);

            if (author == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("Author not found!"));
            }

            return ResponseEntity.ok(author);
        } catch (Exception e)
        {
            logger.error("An error occurred while trying to get author with id: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while trying to get author with id: " + id);
        }

//        Author author = authorService.searchAuthorById(id);
//        model.addAttribute("author", author);
//        return "index";
    }

    // Changed: Updated to return a view name instead of a List<Author>
    @GetMapping("/getAllAuthors")
    public ResponseEntity<?> getAllAuthors(Model model)
    {
        List<Author> authors = authorService.viewAllAuthors();
        try{
            if(authorService.viewAllAuthors().isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No authors found!");
            }
            model.addAttribute("authors", authors);
            return ResponseEntity.ok(authorService.viewAllAuthors());
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while trying to get all authors");
        }

//        List<Author> authors = authorService.viewAllAuthors();
//        model.addAttribute("authors", authors);
//        return "index";
    }

    // Added: New method to handle viewing books by an author
    @GetMapping("/{id}/books")
    public ResponseEntity<?> getAuthorBooks(@PathVariable Long id, Model model) {

        try
        {
            if(authorService.getBooksByAuthor(id).isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("No books found for author with id: " + id));
            }
            return ResponseEntity.ok(authorService.getBooksByAuthor(id));
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while trying to get books for author with id: " + id);
        }
//        Author author = authorService.searchAuthorById(id);
//        List<Book> books = authorService.getBooksByAuthor(id);
//        model.addAttribute("author", author);
//        model.addAttribute("books", books);
//        return "index";
    }
}
