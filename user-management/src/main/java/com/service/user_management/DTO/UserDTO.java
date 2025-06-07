package com.service.user_management.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

class Cart {
    private String productId;
    private int quantity;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String _id;
    private String name;
    private String password;
    private List<Cart> carts;
}
