package com.service.product.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String productName;
    private int price;
    private float rating;
    private String description;
//    private Category category;
//    private byte[] image;
}
