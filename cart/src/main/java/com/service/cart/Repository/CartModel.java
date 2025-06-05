package com.service.cart.Repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "CartInfo")
public class CartModel {
    @Id
    private String _id;
    private String userId;
    private String productId;
    private int quantity;
    private int totalPrice;
    @CreatedDate
    private OffsetDateTime createdAt;
}
