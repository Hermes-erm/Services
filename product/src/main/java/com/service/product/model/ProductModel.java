package com.service.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

enum Category {
    Accessories, Outfits, Electronics, Medical, HealthCare
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("product")
public class ProductModel {
    @Id
    private String _id; // which maps with _id field in mongodb
    private String productName;
    private int price;
    private float rating;
    private String description;
    private Category category;
    private byte[] image;
}
