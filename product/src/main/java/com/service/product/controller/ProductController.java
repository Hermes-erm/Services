package com.service.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.product.DTO.APIResponse;
import com.service.product.model.ProductModel;
import com.service.product.model.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
}
