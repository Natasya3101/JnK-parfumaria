package com.parfumaria.be.services.cart;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.parfumaria.be.dto.products.ProductResponse;
import com.parfumaria.be.dto.user.CartItemsResponse;
import com.parfumaria.be.dto.user.CartRequest;
import com.parfumaria.be.dto.user.CartResponse;
import com.parfumaria.be.models.CartItems;
import com.parfumaria.be.models.Products;
import com.parfumaria.be.models.User;
import com.parfumaria.be.repositorys.CartItemsRepository;
import com.parfumaria.be.repositorys.ProductsRepository;
import com.parfumaria.be.repositorys.UsersRepository;
import com.parfumaria.be.services.image.ImageService;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    CartItemsRepository cartItemsRepository;
    @Autowired
    ImageService imageService;

    @Override
    public CartResponse findAll() {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<CartItems> cartItems = cartItemsRepository.findCartItemsByUser(user);
            System.out.println(cartItems);
            List<CartItemsResponse> cartItemsResponse = cartItems.stream().map(this::toCartItemsResponse)
            .collect(Collectors.toList());
            Integer totalAmount = 0;
            for (CartItemsResponse cartItem : cartItemsResponse) {
                totalAmount += cartItem.getAmount();
            }
            CartResponse response = new CartResponse();
            response.setEmail(user.getEmail());
            response.setTotalAmount(totalAmount);
            response.setCartItems(cartItemsResponse);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ProductResponse toProductResponse(Products product) {
        try {
            return ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .stock(product.getStock())
                    .image(imageService.convertImage(product.getImage()))
                    .category(product.getCategory().getName())
                    .build();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private CartItemsResponse toCartItemsResponse(CartItems cartItems) {
        CartItemsResponse response = new CartItemsResponse();
        response.setId(cartItems.getId());
        response.setQty(cartItems.getQty());
        response.setAmount(cartItems.getAmount());
        response.setProduct(toProductResponse(cartItems.getProduct()));
        return response;
    }

    @Transactional
    @Override
    public void add(CartRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Products product = productsRepository.findProductsById(request.getProductId());
        System.out.println(user);
        CartItems cartItems = cartItemsRepository.findCartItemsByUserAndProduct(user, product);
        System.out.println(cartItems);
        if (cartItems == null) {
            CartItems newCartItems = new CartItems();
            newCartItems.setUser(user);
            newCartItems.setProduct(product);
            newCartItems.setQty(request.getQuantity());
            newCartItems.setAmount(product.getPrice() * newCartItems.getQty());
            System.out.println(cartItems);
            cartItemsRepository.save(newCartItems);
        } else {
            cartItems.setQty(cartItems.getQty() + request.getQuantity());
            cartItems.setAmount(cartItems.getQty() * product.getPrice());
            System.out.println(cartItems);
            cartItemsRepository.save(cartItems);
        }
    }

    @Override
    public void decreaseQuantity(CartRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Products product = productsRepository.findProductsById(request.getProductId());
        CartItems cartItem = cartItemsRepository.findCartItemsByUserAndProduct(user, product);
        cartItem.setQty(cartItem.getQty() - request.getQuantity());
        cartItem.setAmount(cartItem.getQty() * product.getPrice());
        cartItemsRepository.save(cartItem);
    }

    @Override
    public void increaseQuantity(CartRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Products product = productsRepository.findProductsById(request.getProductId());
        CartItems cartItem = cartItemsRepository.findCartItemsByUserAndProduct(user, product);
        cartItem.setQty(cartItem.getQty() + request.getQuantity());
        cartItem.setAmount(cartItem.getQty() * product.getPrice());
        cartItemsRepository.save(cartItem);
    }

    @Override
    public void deleteCartItems(String id) {
        cartItemsRepository.deleteById(id);
    }
}
