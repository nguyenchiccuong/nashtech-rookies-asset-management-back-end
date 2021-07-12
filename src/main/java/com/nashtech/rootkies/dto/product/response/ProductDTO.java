package com.nashtech.rootkies.dto.product.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDTO {

    private Long id;
    private String name;
    private double originalPrice;
    private double discountPrice;
    private Long categoryId;
    private String description;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private boolean isDeleted;
    private int quantity;
    private boolean isInStock;
}
