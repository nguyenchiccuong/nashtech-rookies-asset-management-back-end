package com.nashtech.rootkies.dto.cart.response;

import com.nashtech.rootkies.dto.cart.request.CartItemDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
public class CartDTO {

    private Long id;

    private Long userId;

    private List<CartItemDTO> cartItems;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;


}
