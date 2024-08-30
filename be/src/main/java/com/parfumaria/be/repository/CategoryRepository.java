package com.parfumaria.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parfumaria.be.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoryByName(String name);
}
