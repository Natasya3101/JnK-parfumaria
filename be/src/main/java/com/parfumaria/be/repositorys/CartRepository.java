package com.parfumaria.be.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parfumaria.be.models.Cart;
import com.parfumaria.be.models.CartItems;
import com.parfumaria.be.models.User;


public interface CartRepository extends JpaRepository<Cart, String>{
    Cart findCartByUser(User user);
    Cart findCartByCartItems(CartItems cartItems);
}
