package com.librarymanagementsystem.java.service.serviceImpl;

import com.librarymanagementsystem.java.entity.Book;
import com.librarymanagementsystem.java.entity.Categories;
import com.librarymanagementsystem.java.repository.BookRepository;
import com.librarymanagementsystem.java.repository.CategoryRepository;
import com.librarymanagementsystem.java.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void addCategory(Long bookId, Categories category) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.getCategories().add(category);
        category.getBooks().add(book); // This line maintains the bidirectional relationship
        bookRepository.save(book);;
    }

    @Override
    public List<Categories> viewAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Categories searchCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
