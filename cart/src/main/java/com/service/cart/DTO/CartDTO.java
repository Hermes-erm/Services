package com.service.cart.DTO;

import lombok.Data;

@Data
public class CartDTO {
    private String userId;
    private String productId;
    private int quantity;
//    private double price; // for later..
}