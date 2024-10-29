package com.librarymanagementsystem.java.controller;


import com.librarymanagementsystem.java.entity.Categories;
import com.librarymanagementsystem.java.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping("/viewAll")
    public ResponseEntity<?> viewAllCategories(Model model) {
        try
        {
            if(categoryService.viewAllCategories().isEmpty())
            {
                return ResponseEntity.ok("No categories found");
            }
            return ResponseEntity.ok(categoryService.viewAllCategories());
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred while fetching the data! " + e);
        }
//        model.addAttribute("categories", categoryService.viewAllCategories());
//        return "index";
    }

    @GetMapping("/searchById/{id}")
    public ResponseEntity<?> searchCategoryById(@PathVariable Long id, Model model) {
        try {
            Categories categories = categoryService.searchCategoryById(id);

            if (categories == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("Category not found!"));
            }

            return ResponseEntity.ok(categoryService.searchCategoryById(id));
        } catch (Exception e)
        {
            logger.error("An error occurred while trying to get author with id: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while trying to get author with id: " + id);
        }

//        Categories categories = categoryService.searchCategoryById(id);
//        model.addAttribute("categories", categories);
//        return "index";
    }
}
