package com.service.cart.Controller;

import com.service.cart.DTO.APIResponse;
import com.service.cart.Repository.CartModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart") // base path (class-level mapping)
public class CartController {

    @PostMapping("/new-product") // using servlet container / servlet APIs under the hood
    private ResponseEntity<APIResponse> newProduct(@RequestBody CartModel cartDetails) { // handler/controller methods
        
        return ResponseEntity.ok(new APIResponse("Product added to cart", cartDetails));
    }
}
