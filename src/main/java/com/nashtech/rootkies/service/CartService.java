package com.nashtech.rootkies.service;

import com.nashtech.rootkies.dto.cart.response.CartDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.exception.UpdateDataFailException;
import com.nashtech.rootkies.model.Cart;

import java.text.ParseException;

public interface CartService {

    CartDTO getCart(Long id) throws ParseException;
    boolean addItemToCart(Long userId , Long productId) throws DataNotFoundException, UpdateDataFailException;
    boolean removeItem(Long userId, Long productId) throws DataNotFoundException, UpdateDataFailException;
    boolean update(Cart convertCartDtoToEntity);
}
