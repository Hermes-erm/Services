package com.service.product.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.DTO.APIResponse;
import com.service.product.model.ProductModel;
import com.service.product.model.ProductRepo;

@RestController
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/new-product")
    ResponseEntity<String> postProduct(@RequestPart("productDetails") String product,
            @RequestPart("productImage") MultipartFile imageFile) throws IOException, JsonProcessingException {
        // this.productRepo.save(product);

        ProductModel newProduct = objectMapper.readValue(product, ProductModel.class);
        // System.err.println(imageFile.getBytes());

        return ResponseEntity.ok("okay");
        // return new ResponseEntity<>(new APIresponse("product received!", null),
        // HttpStatus.OK);
    }
}
