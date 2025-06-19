package com.service.cart.Service;

import com.service.cart.DTO.APIResponse;
import com.service.cart.DTO.CartDTO;
import com.service.cart.Repository.Cart;
import com.service.cart.Repository.CartRepository;
import com.service.cart.Repository.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CartService {

    private class Utils {
        public static int quantityOfProduct(List<Cart> products) {
            int amt = 0;
            return amt;
        }

        public static int isProductExist(List<Cart> products, String productId) {
            return -1;
        }
    }

    @Autowired
    private CartRepository repository;

    private static ResponseEntity<APIResponse> response(HttpStatus status, String message, Object data) {
        return ResponseEntity.status(status).body(new APIResponse(message, data));
    }

    public ResponseEntity<APIResponse> addNewItem(CartDTO product) {
        Optional<UserModel> isUser = this.repository.findById(product.getUserId()); // upsert with save();
        if (isUser.isEmpty()) return response(HttpStatus.NOT_FOUND, "user not found", null);
        UserModel user = isUser.get();
        List<Cart> cartList = user.getCarts();

        int ind = Utils.isProductExist(cartList, product.getProductId());
        if (ind >= 0) cartList.set(ind, new Cart(product.getProductId(), Utils.quantityOfProduct(cartList)));
        else cartList.add(new Cart(product.getProductId(), 0));

        return response(HttpStatus.ACCEPTED, "Cart received", cartList);
    }
}
