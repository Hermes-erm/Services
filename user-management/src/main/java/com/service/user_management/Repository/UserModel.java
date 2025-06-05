package com.service.user_management.Repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

class Cart {
    private String productId;
    private int quantity;
}

@Document(value = "User")
public class UserModel {
    @Id
    private String _id;
    private String name;
    private String password;
    private List<Cart> carts;
}
