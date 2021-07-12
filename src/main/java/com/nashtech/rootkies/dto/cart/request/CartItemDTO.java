package com.nashtech.rootkies.dto.cart.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CartItemDTO {

    private Long id;
    private String name;
    private Long productId;
    private int quantity;
    private double price;

}
