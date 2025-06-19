package com.service.cart.Controller;

import com.service.cart.DTO.APIResponse;
import com.service.cart.DTO.CartDTO;
import com.service.cart.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart") // base path (class-level mapping)
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/new-product") // using servlet container / servlet APIs under the hood
    private ResponseEntity<APIResponse> newProduct(@RequestBody CartDTO cartItem) { // handler/controller methods
        return this.cartService.addNewItem(cartItem);
    }
}
