package com.service.product.controller;

import com.service.product.DTO.APIResponse;
import com.service.product.model.ProductModel;
import com.service.product.model.ProductRepo;
import com.service.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/inventory")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductService productService;


    @PostMapping("/new-product")
    ResponseEntity<APIResponse> postProduct(@RequestPart("productDetails") String product,
                                            @RequestPart("productImage") MultipartFile imageFile) throws IOException {
        return ResponseEntity.ok(new APIResponse("Data received", this.productService.saveProduct(product, imageFile)));
    }

    @GetMapping("/all-products")
    private ResponseEntity<APIResponse> getAllProducts() {
        return ResponseEntity.ok(new APIResponse("Data sent!", this.productService.getProducts()));
    }

    @GetMapping("/product/{productId}")
    private ResponseEntity<APIResponse> getSoleProduct(@PathVariable String productId) {
        return ResponseEntity.ok(new APIResponse("Product sent!", this.productService.getProduct(productId)));
    }

    @GetMapping("/image/{productId}") // not gonna used anywhere..
    private ResponseEntity<byte[]> getProductImage(@PathVariable String productId) { // return binary (sequence of bytes)
        Optional<ProductModel> product = this.productRepo.findById(productId);
        byte[] productImage = product.isPresent() ? product.get().getImage() : null;
//        System.err.println(product.get().getProductName());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).contentLength(productImage.length) // might optional
                .body(productImage);
    }
}
