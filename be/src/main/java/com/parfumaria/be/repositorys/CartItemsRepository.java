package com.parfumaria.be.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parfumaria.be.models.CartItems;
import com.parfumaria.be.models.Products;
import com.parfumaria.be.models.User;

public interface CartItemsRepository extends JpaRepository<CartItems, String>{
    CartItems findCartItemsById(String id);
    CartItems findCartItemsByUserAndProduct(User user, Products product);
    CartItems findCartItemsByProduct(Products product);
    List<CartItems> findCartItemsByUser(User user);
}
