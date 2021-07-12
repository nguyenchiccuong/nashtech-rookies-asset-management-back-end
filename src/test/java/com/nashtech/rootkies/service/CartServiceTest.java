package com.nashtech.rootkies.service;

import com.nashtech.rootkies.dto.cart.request.CartItemDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.exception.UpdateDataFailException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTest {

    @Autowired
    CartService cartService;

    @Test
    public void testAddNewItemToCartSuccess() throws DataNotFoundException, UpdateDataFailException {
        assertEquals(true , cartService.addItemToCart(38L , 19L));
    }

    @Test
    public void testAddDuplicateItemInCart() throws DataNotFoundException, UpdateDataFailException, ParseException {
        List<CartItemDTO> items = cartService.getCart(50L).getCartItems();
        assertEquals(true , cartService.addItemToCart(38L , 10L));
    }

    @Test
    public void testRemoveItemInCart() throws DataNotFoundException, UpdateDataFailException, ParseException {
        List<CartItemDTO> items = cartService.getCart(50L).getCartItems();
        assertEquals(true , cartService.addItemToCart(38L , 10L));
    }




}
