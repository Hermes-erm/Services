package com.service.cart.Repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "User")
public class UserModel { // alias as cart
    @Id
    private String _id;
    private String name;
    private String password;
    private List<Cart> carts;
}
