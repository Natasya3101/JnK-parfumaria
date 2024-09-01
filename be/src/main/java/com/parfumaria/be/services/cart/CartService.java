package com.parfumaria.be.services.cart;

import com.parfumaria.be.dto.user.CartRequest;
import com.parfumaria.be.dto.user.CartResponse;

public interface CartService {
    void add(CartRequest request);
    CartResponse findAllCart();
    void deleteAll();
    void deleteCartItems(String id);
}
