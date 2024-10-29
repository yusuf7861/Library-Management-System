package com.librarymanagementsystem.java.repository;

import com.librarymanagementsystem.java.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {
}
