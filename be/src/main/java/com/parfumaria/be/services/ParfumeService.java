package com.parfumaria.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.JpaSort.Path;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.parfumaria.be.model.Parfume;
import com.parfumaria.be.repository.ParfumeRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Service
public class ParfumeService {
    


    @Autowired
    private ParfumeRepository parfumeRepository;

    public void add(ProductRequest request, MultipartFile file) throws IOException {
        // Save the file to the server and get the file path
        String filePath = saveImage(file);

        // Create a new Parfume object
        Parfume parfume = new Parfume();
        parfume.setName(request.getName());
        parfume.setStock(request.getStock());
        parfume.setPrice(request.getPrice());
        parfume.setPict(filePath);
        parfume.setIdCategory(request.getIdCategory());

        // Save the Parfume object to the database
        parfumeRepository.save(parfume);
    }

    private String saveImage(MultipartFile file) throws IOException {
        Path uploadDirectory = Paths.get("images");
        if (!Files.exists(uploadDirectory)) {
            Files.createDirectories(uploadDirectory);
        }

        String fileName = file.getOriginalFilename();
        Path destinationFile = uploadDirectory.resolve(fileName).normalize().toAbsolutePath();
        file.transferTo(destinationFile.toFile());

        return destinationFile.toString(); // Returning the file path
    }
}
