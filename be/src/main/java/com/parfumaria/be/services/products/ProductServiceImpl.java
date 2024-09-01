package com.parfumaria.be.services.products;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.parfumaria.be.dao.ProductDao;
import com.parfumaria.be.dto.PageResponse;
import com.parfumaria.be.dto.products.ProductRequest;
import com.parfumaria.be.dto.products.ProductResponse;
import com.parfumaria.be.models.Category;
import com.parfumaria.be.models.Products;
import com.parfumaria.be.repositorys.CategoryRepository;
import com.parfumaria.be.repositorys.ProductsRepository;
import com.parfumaria.be.services.image.ImageService;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ImageService imageService;

    @Override
    public PageResponse<ProductResponse> getAllProducts(String name, String category, String sortBy,
            String sortOrder, Integer minPrice, Integer maxPrice) {
        Category categoryName = categoryRepository.findCategoryByName(category);
        PageResponse<Products> productPage = productDao.getAll(name, categoryName, sortBy, sortOrder, minPrice, maxPrice);
        List<ProductResponse> productResponse = productPage.getItems().stream()
                .map(this::toProduct)
                .collect(Collectors.toList());
        return PageResponse.success(productResponse, productPage.getTotalItem());
    }

    public ProductResponse toProduct(Products product) {
        try {
            return ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .stock(product.getStock())
                    .image(imageService.convertImage(product.getImage()))
                    .category(product.getCategory().getName())
                    .build();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ProductResponse getProduct(Integer id) {
        Products products = productsRepository.findProductsById(id);
        return toProduct(products);
    }

    @Override
    @Transactional
    public void add(ProductRequest request, MultipartFile productImage) throws IOException, SQLException {
        if (!productImage.getContentType().startsWith("image")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unsupported File Type");
        }
        Products product = productsRepository.findByName(request.getName()).orElse(null);
        if (product == null) {
            Products newProduct = new Products();
            newProduct.setName(request.getName());
            Category category = categoryRepository.findCategoryByName(request.getCategory());
            if (category != null) {
                newProduct.setCategory(category);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category Tidak Ada");
            }
            newProduct.setPrice(request.getPrice());
            newProduct.setStock(request.getStock());
            newProduct.setImage(new SerialBlob(productImage.getBytes()));
            productsRepository.save(newProduct);
        } else {
            product.setCategory(categoryRepository.findCategoryByName(request.getCategory()));
            product.setStock(product.getStock() + request.getStock());
            productsRepository.save(product);
        }
    }
}