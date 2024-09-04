package com.parfumaria.be.services.cart;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.parfumaria.be.dto.products.ProductResponse;
import com.parfumaria.be.dto.user.CartItemsResponse;
import com.parfumaria.be.dto.user.CartRequest;
import com.parfumaria.be.dto.user.CartResponse;
import com.parfumaria.be.models.Cart;
import com.parfumaria.be.models.CartItems;
import com.parfumaria.be.models.Products;
import com.parfumaria.be.models.User;
import com.parfumaria.be.repositorys.CartItemsRepository;
import com.parfumaria.be.repositorys.CartRepository;
import com.parfumaria.be.repositorys.ProductsRepository;
import com.parfumaria.be.repositorys.UsersRepository;
import com.parfumaria.be.services.image.ImageService;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartItemsRepository cartItemsRepository;
    @Autowired
    ImageService imageService;

    @Override
    public CartResponse findAll() {
            // User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            // System.out.println("ini adalah user : " + user);
            System.out.println(cartRepository.findAll());
            // Cart cart = cartRepository.findCartByUser(user);
            // System.out.println(cart);
            // List<CartItemsResponse> cartItemsResponse = cart.getCartItems().stream().map(this::toCartItemsResponse)
            //         .collect(Collectors.toList());
            // Integer totalAmount = 0;
            // for (CartItemsResponse cartItem : cartItemsResponse) {
            //     totalAmount += cartItem.getAmount();
            // }
            // CartResponse response = new CartResponse();
            // response.setId(cart.getId());
            // response.setEmail(user.getEmail());
            // response.setTotalAmount(totalAmount);
            // response.setCartItems(cartItemsResponse);
            return null;
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

    @Override
    public void add(CartRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Cart cart = cartRepository.findCartByUser(user);

        Products product = productsRepository.findProductsById(request.getProductId());
        if (request.getQuantity() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Qty");
        } else if (product.getStock() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Out of Stock");
        }
        if (product.getStock() < request.getQuantity()) {
            request.setQuantity(product.getStock());
        }

        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // User user = usersRepository.findUserByEmail(auth.getName());

        if (cart == null) {
            Cart newCart = new Cart();
            newCart.setUser(user);
            cartRepository.save(newCart);

            CartItems cartItems = new CartItems();
            cartItems.setQty(request.getQuantity());
            cartItems.setAmount(request.getQuantity() * product.getPrice());
            cartItems.setProduct(product);
            cartItemsRepository.save(cartItems);

            newCart.getCartItems().add(cartItems);
            cartRepository.save(newCart);
        } else {
            // // jika product sudah ada di keranjang user
            Boolean same = false;
            Set<CartItems> cartItems = cart.getCartItems();
            for (CartItems items : cartItems) {
                if (items.getProduct().equals(product)) {
                    same = true;
                    if ((request.getQuantity() + items.getQty()) > product.getStock()) {
                        items.setQty(product.getStock());
                    } else {
                        items.setQty(request.getQuantity() + items.getQty());
                    }
                    items.setAmount(product.getPrice() * items.getQty());
                    break;
                }
            }
            if (same) {
                cart.setCartItems(cartItems);
                cartRepository.save(cart);
            } else {
                // jika product tidak ada di keranjang user
                CartItems buffCartItems = new CartItems();
                buffCartItems.setQty(request.getQuantity());
                buffCartItems.setAmount(request.getQuantity() * product.getPrice());
                buffCartItems.setProduct(product);
                cartItemsRepository.save(buffCartItems);
                cart.getCartItems().add(buffCartItems);
                cartRepository.save(cart);
            }
        }
    }

    @Override
    public void deleteAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = usersRepository.findUserByEmail(auth.getName());
        Cart cart = cartRepository.findCartByUser(user);
        if (cart == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your Cart Is Empty");
        }
        Set<CartItems> cartItems = cart.getCartItems();
        System.out.println(cartItems);
        cartRepository.delete(cart);
        cartItems.forEach(i -> cartItemsRepository.delete(i));
    }

    @Override
    public void deleteCartItems(String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = usersRepository.findUserByEmail(auth.getName());
        Cart cart = cartRepository.findCartByUser(user);
        CartItems cartItems = cartItemsRepository.findCartItemsById(id);
        for (CartItems c : cart.getCartItems()) {
            if (c.getId().equals(cartItems.getId())) {
                cart.getCartItems().remove(c);
                cartRepository.save(cart);
                cartItemsRepository.delete(cartItems);
                break;
            }
        }
    }
}
