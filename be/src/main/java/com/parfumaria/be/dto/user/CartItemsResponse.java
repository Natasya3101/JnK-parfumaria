package com.parfumaria.be.dto.user;


import com.parfumaria.be.dto.products.ProductResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemsResponse {
    private String id;
    private Integer qty;
    private Integer amount;
    private ProductResponse product;
}
