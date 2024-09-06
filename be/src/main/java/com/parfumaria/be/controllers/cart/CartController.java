package com.parfumaria.be.controllers.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.parfumaria.be.dto.GenericResponse;
import com.parfumaria.be.dto.user.CartRequest;
import com.parfumaria.be.services.cart.CartService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/cart")
@Tag(name = "Cart")
@CrossOrigin(origins = "http://localhost:5173/")
@Slf4j
public class CartController {
    
    @Autowired
    CartService cartService;

    @GetMapping("/find-all")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> findAll() {
        try {
            return ResponseEntity.ok().body(GenericResponse.success(cartService.findAll(), "Successfully Get Cart"));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(GenericResponse.eror(e.getLocalizedMessage()));
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.internalServerError().body(GenericResponse.eror("Internal Server Error!"));
        }
    }

    @PutMapping("/increase-quantity")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> increaseQuantity(@RequestBody CartRequest request) {
        try {
            cartService.increaseQuantity(request);
            return ResponseEntity.ok().body(GenericResponse.success(null, "Successfully Decrease Quantity Cart Item"));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(GenericResponse.eror(e.getReason()));
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.internalServerError().body(GenericResponse.eror("Internal Server Error!"));
        }
    }
    @PutMapping("/decrease-quantity")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> decreaseQuantity(@RequestBody CartRequest request) {
        try {
            cartService.decreaseQuantity(request);
            return ResponseEntity.ok().body(GenericResponse.success(null, "Successfully Decrease Quantity Cart Item"));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(GenericResponse.eror(e.getReason()));
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.internalServerError().body(GenericResponse.eror("Internal Server Error!"));
        }
    }

    @PostMapping("/add-cart-items")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> addCart(@RequestBody CartRequest request) {
        try {
            cartService.add(request);
            return ResponseEntity.ok().body(GenericResponse.success(null, "Successfully Add Cart"));
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.internalServerError().body(GenericResponse.eror("Internal Server Error!"));
        }
    }

    @DeleteMapping("/delete-cart-items/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> removeCartItems(@PathVariable(value = "id") String id) {
        try {
            cartService.deleteCartItems(id);
            return ResponseEntity.ok().body(GenericResponse.success(null, "Successfully Delete cart items"));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(GenericResponse.eror(e.getReason()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.eror("Internal Server Error!"));
        }
    }
}