package com.service.product.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.product.model.ProductModel;
import com.service.product.model.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ObjectMapper objectMapper;

    private ProductModel tempProduct;

    public ProductModel saveProduct(String product, MultipartFile imageFile) throws IOException {
        ProductModel newProduct = objectMapper.readValue(product, ProductModel.class);
        newProduct.setImage(imageFile.getBytes());
        this.productRepo.save(newProduct);
        newProduct.setImage(null); // or just use @JsonIgnore or @JsonProperty(access = Access.WRITE_ONLY)!
        return newProduct;
    }

    public List<ProductModel> getProducts() {
        List<ProductModel> products = this.productRepo.findAll();
        return products;
    }

    public ProductModel getProduct(String productId) {
        Optional<ProductModel> product = this.productRepo.findById(productId);
        ProductModel isProduct = product.isPresent() ? product.get() : null;
        return isProduct;
    }
}
