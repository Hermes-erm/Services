package com.service.cart.Repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    //    @DocumentReference(lazy = false)
    public String productId;
    private int quantity;
}
