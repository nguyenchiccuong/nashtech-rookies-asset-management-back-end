package com.nashtech.rootkies.dto.product.request;

import lombok.Data;

@Data
public class CreateProductDTO {

    private String name;
    private double originalPrice;
    private double discountPrice;
    private Long categoryId;
    private Long brandId;
    private String description;
    private String content;
    private int quantity;
    private boolean isInStock;

}
