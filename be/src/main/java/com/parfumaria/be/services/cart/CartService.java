package com.parfumaria.be.services.cart;

import com.parfumaria.be.dto.user.CartRequest;
import com.parfumaria.be.dto.user.CartResponse;

public interface CartService {
    void add(CartRequest request);
    void decreaseQuantity(CartRequest request);
    void increaseQuantity(CartRequest request);
    CartResponse findAll();
    void deleteCartItems(String id);
}
