package com.service.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("product")
public class ProductModel {
    private String productName;
    private int price;
    private float rating;
    private String description;
    private byte[] image;
}
