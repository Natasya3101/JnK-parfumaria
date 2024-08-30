package com.parfumaria.be.controller.product;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.parfumaria.be.dto.GenericResponse;
import com.parfumaria.be.dto.products.ProductRequest;
import com.parfumaria.be.services.product.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api")
@RestController
@Tag(name = "Parfume")
public class ParfumeController {

    @Autowired
    private ProductService parfumeService;

    @PostMapping(value = "/product/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> addProduct(ProductRequest request, @RequestParam("Product Image") MultipartFile file) {
        try {
            parfumeService.add(request, file);
            return ResponseEntity.ok().body(GenericResponse.success(null, "Success Add New Product"));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(GenericResponse.eror(e.getReason()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.eror("Internal Server Error!"));
        }
    }
}
