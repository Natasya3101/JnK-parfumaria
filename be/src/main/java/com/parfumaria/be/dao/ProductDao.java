package com.parfumaria.be.dao;

import com.parfumaria.be.dto.PageResponse;
import com.parfumaria.be.models.Category;
import com.parfumaria.be.models.Products;

public interface ProductDao {
    PageResponse<Products> getAll(String name, Category category, String sortBy, String sortOrder, Integer minPrice, Integer maxPrice);
}
