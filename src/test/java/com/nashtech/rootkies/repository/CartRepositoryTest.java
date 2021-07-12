package com.nashtech.rootkies.repository;


import com.nashtech.rootkies.model.Cart;
import com.nashtech.rootkies.model.CartItem;
import com.nashtech.rootkies.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartRepositoryTest {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testCreateNewCartSuccess(){

        Cart cart = new Cart();
        List<CartItem> cartItems = new ArrayList<CartItem>();
        CartItem item1 = new CartItem();
        item1.setProduct(productRepository.findById(10L).get());
        item1.setCreatedDate(LocalDateTime.now());
        item1.setQuantity(1);
        item1.setPrice(700);
        item1.setCart(cart);
        cartItems.add(item1);
        cart.setCartItems(cartItems);
        Cart saveCart = cartRepository.save(cart);
        assertNotNull(saveCart);
        assertEquals(1, saveCart.getCartItems().size());
    }

    @Test
    public void testCreateCartOfExistingUser(){
        User user = userRepository.findById(38L).get();
        Cart cart = new Cart();
        List<CartItem> cartItems = new ArrayList<CartItem>();
        CartItem item1 = new CartItem();
        item1.setProduct(productRepository.findById(10L).get());
        item1.setCreatedDate(LocalDateTime.now());
        item1.setQuantity(1);
        item1.setPrice(500);
        item1.setCart(cart);
        cartItems.add(item1);
        cart.setCartItems(cartItems);
        cart.setUser(user);
        Cart saveCart = cartRepository.save(cart);
        assertNotNull(saveCart);
        assertEquals(1, saveCart.getCartItems().size());
    }

    @Test
    public void testUpdateCart(){
        User user = userRepository.findById(38L).get();
        Cart cart = cartRepository.findById(50L).get();
    }

}
