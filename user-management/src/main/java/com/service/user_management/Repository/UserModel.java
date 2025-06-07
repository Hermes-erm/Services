package com.service.user_management.Repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

class Cart {
    //    @DocumentReference(lazy = true)
    public String productId;
    private int quantity;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "User")
public class UserModel {
    @Id
    private String _id;
    private String name;
    private String password;
    private List<Cart> carts;
}
