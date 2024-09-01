package com.parfumaria.be.services.products;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.multipart.MultipartFile;

import com.parfumaria.be.dto.PageResponse;
import com.parfumaria.be.dto.products.ProductRequest;
import com.parfumaria.be.dto.products.ProductResponse;

public interface ProductService {
    PageResponse<ProductResponse> getAllProducts(String name, String category, String sortBy, String sortOrder, Integer minPrice, Integer maxPrice);
    ProductResponse getProduct(Integer id);
    void add(ProductRequest request,MultipartFile productImage) throws IOException, SQLException;
}
