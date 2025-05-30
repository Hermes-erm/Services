package com.service.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.product.DTO.APIResponse;
import com.service.product.model.ProductModel;
import com.service.product.model.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.print.DocFlavor.READER;

@RestController
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/new-product")
    ResponseEntity<APIResponse> postProduct(@RequestPart("productDetails") String product,
            @RequestPart("productImage") MultipartFile imageFile) throws IOException {
        ProductModel newProduct = objectMapper.readValue(product, ProductModel.class);
        newProduct.setImage(imageFile.getBytes());
        this.productRepo.save(newProduct);

        newProduct.setImage(null); // or just use @JsonIgnore or @JsonProperty(access = Access.WRITE_ONLY)!

        return ResponseEntity.ok(new APIResponse("Data received", newProduct));
        // return new ResponseEntity<>(new APIresponse("product received!", null),
        // HttpStatus.OK);
    }

    @GetMapping("/all-products")
    private ResponseEntity<APIResponse> getAllProducts() {
        List<ProductModel> products = this.productRepo.findAll();
        return ResponseEntity.ok(new APIResponse("Data sent!", products));
    }

    @GetMapping("/product/{productId}")
    private ResponseEntity<APIResponse> getSoleProduct(@PathVariable String productId) {
        Optional<ProductModel> product = this.productRepo.findById(productId);
        ProductModel isProduct = product.isPresent() ? product.get() : null;
        return ResponseEntity.ok(new APIResponse("Product sent!", isProduct));
    }

    @GetMapping("/image/{productId}")
    private ResponseEntity<byte[]> getProductImage(@PathVariable String productId) {
        Optional<ProductModel> product = this.productRepo.findById(productId);
        byte[] productImage = product.isPresent() ? product.get().getImage() : null;
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).contentLength(productImage.length)
                .body(productImage);
    }
}
