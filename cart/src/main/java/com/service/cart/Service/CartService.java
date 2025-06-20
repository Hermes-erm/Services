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

        int ind = Utils.getProductPos(cartList, product.getProductId());
        if (ind >= 0) {
            int amt = Utils.quantityOfProduct(cartList.get(ind));
            cartList.set(ind, new Cart(product.getProductId(), amt));
        } else cartList.add(new Cart(product.getProductId(), 1));
        this.repository.save(user);

        return response(HttpStatus.ACCEPTED, "Cart updated!", cartList);
    }

    private static class Utils {
        public static int quantityOfProduct(Cart product) {
            return product.getQuantity() + 1;
        }

        public static int getProductPos(List<Cart> products, String productId) {
            if (products.isEmpty()) return -1;
            for (int i = 0; i < products.size(); i++)
                if (products.get(i).getProductId().equals(productId)) return i;
            return -1;
        }
    }
}
