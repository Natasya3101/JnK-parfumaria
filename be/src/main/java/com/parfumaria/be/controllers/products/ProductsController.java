package com.parfumaria.be.controllers.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.parfumaria.be.dto.GenericResponse;
import com.parfumaria.be.dto.PageResponse;
import com.parfumaria.be.dto.products.ProductRequest;
import com.parfumaria.be.dto.products.ProductResponse;
import com.parfumaria.be.services.products.ProductService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api/products")
@RestController
@Tag(name = "products")
@CrossOrigin(origins = "http://localhost:5173/")
public class ProductsController {
    @Autowired
    ProductService productService;

    @GetMapping("/get-all-products")
    public ResponseEntity<Object> getAll(@RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortOrder,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice) {
        try {
            PageResponse<ProductResponse> response = productService.getAllProducts(name, category, sortBy,
                    sortOrder, minPrice, maxPrice);
            return ResponseEntity.ok().body(GenericResponse.success(response, "Success Get All Product"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.eror(e.getMessage()));
        }
    }

    @GetMapping("/get-product/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable(value = "id") Integer id) {
        try {
            return ResponseEntity.ok()
                    .body(GenericResponse.success(productService.getProduct(id), "Success Get Product"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.eror("Internal Server Error!"));
        }
    }

    @PostMapping(value = "/add-product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> addProduct(ProductRequest request,
            @RequestParam("Product Image") MultipartFile file) {
        try {
            productService.add(request, file);
            return ResponseEntity.ok().body(GenericResponse.success(null, "Success Add New Product"));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(GenericResponse.eror(e.getReason()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.eror("Internal Server Error!"));
        }
    }
}