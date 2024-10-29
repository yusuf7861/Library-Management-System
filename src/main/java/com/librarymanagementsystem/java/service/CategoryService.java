package com.librarymanagementsystem.java.service;

import com.librarymanagementsystem.java.entity.Categories;

import java.util.List;

public interface CategoryService {
    public void addCategory(Long bookId, Categories category);
    public List<Categories> viewAllCategories();
    public Categories searchCategoryById(Long id);
}
