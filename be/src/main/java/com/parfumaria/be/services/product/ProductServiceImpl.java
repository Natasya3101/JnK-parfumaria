package com.parfumaria.be.services.product;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


import com.parfumaria.be.dto.products.ProductRequest;
import com.parfumaria.be.model.Category;
import com.parfumaria.be.model.Parfume;
import com.parfumaria.be.repository.CategoryRepository;
import com.parfumaria.be.repository.ParfumeRepository;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ParfumeRepository parfumeRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void add(ProductRequest request, MultipartFile productImage) throws IOException, SQLException {
        // cek format file
        if (!productImage.getContentType().startsWith("image")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unsupported File Type");
        }
        // Create a new Parfume object
        Parfume parfume = new Parfume();
        Category category = categoryRepository.findCategoryByName(request.getCategory());
        if (category != null) {
            parfume.setCategory(category);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category Tidak Ada");
        }
        parfume.setName(request.getName());
        parfume.setStock(request.getStock());
        parfume.setPrice(request.getPrice());
        parfume.setPict(new SerialBlob(productImage.getBytes()));
        parfumeRepository.save(parfume);
    }

}
