package com.service.product.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductRepo extends MongoRepository<ProductModel, String> {
}
