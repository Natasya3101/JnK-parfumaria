package com.parfumaria.be.services.product;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.multipart.MultipartFile;

import com.parfumaria.be.dto.products.ProductRequest;

public interface ProductService {
    void add(ProductRequest request,MultipartFile productImage) throws IOException, SQLException;
}
