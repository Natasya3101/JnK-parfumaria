package com.parfumaria.be.repositorys;


import org.springframework.data.jpa.repository.JpaRepository;

import com.parfumaria.be.models.CartItems;

public interface CartItemsRepository extends JpaRepository<CartItems, String>{
    CartItems findCartItemsById(String id);
}
