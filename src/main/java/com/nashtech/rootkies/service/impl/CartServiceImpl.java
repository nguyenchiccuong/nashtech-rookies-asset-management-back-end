package com.nashtech.rootkies.service.impl;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.converter.CartConverter;
import com.nashtech.rootkies.dto.cart.response.CartDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.exception.UpdateDataFailException;
import com.nashtech.rootkies.model.Cart;
import com.nashtech.rootkies.model.CartItem;
import com.nashtech.rootkies.model.Product;
import com.nashtech.rootkies.repository.CartItemRepository;
import com.nashtech.rootkies.repository.CartRepository;
import com.nashtech.rootkies.repository.ProductRepository;
import com.nashtech.rootkies.repository.UserRepository;
import com.nashtech.rootkies.service.CartService;
import com.nashtech.rootkies.validator.CartValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartConverter cartConverter;

    @Autowired
    CartValidator cartValidator;

    private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

    /**
     *
     * @param id
     * @return
     */
    @Override
    public CartDTO getCart(Long id) throws ParseException {

        Cart cart = cartRepository.findById(id).get();

        return cartConverter.convertCartEntityToDTO(cart);
    }

    /**
     *
     * @param userId
     * @param productId
     * @return
     * @throws DataNotFoundException
     * @throws UpdateDataFailException
     */
    @Override
    public boolean addItemToCart(Long userId, Long productId) throws DataNotFoundException, UpdateDataFailException {

        try{
            if(!userRepository.findById(userId).isPresent()){
                LOGGER.info("Not found User with id: %s" , userId);
                throw new DataNotFoundException(ErrorCode.ERR_USER_NOT_FOUND);
            }
            LOGGER.info("Validated product before add to cart userId: %s , productId: %s" , userId, productId);
            Cart cart = cartRepository.findByUserId(userId).orElse(cart = new Cart());
            Product selectedProduct =productRepository.findById(productId).get();

            List<CartItem> items = cart.getCartItems().stream().filter(item -> item.getProduct().getId() == productId).collect(Collectors.toList());
            CartItem addedItem = null;
            if(items.size() == 0){
                addedItem = new CartItem();
            }else{
                addedItem = items.get(0);
            }
            addedItem.setProduct(selectedProduct);
            addedItem.setPrice(selectedProduct.getDiscountPrice() > 0
                    ? selectedProduct.getDiscountPrice() : selectedProduct.getOriginalPrice());
            addedItem.setName(selectedProduct.getName());
            addedItem.setCart(cart);
            addedItem.setQuantity(addedItem.getQuantity() + 1);
            cart.getCartItems().add(addedItem);
            cart.setUpdatedDate(LocalDateTime.now());
            cartRepository.save(cart);
        }catch(Exception ex){
            LOGGER.info("Fail to add product %s into Cart of User with id: %s" , userId , productId);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_CART_FAIL);
        }
        return true;
    }

    /**
     *
     * @param userId
     * @param productId
     * @return
     * @throws UpdateDataFailException
     */
    @Override
    public boolean removeItem(Long userId, Long productId) throws UpdateDataFailException {
        try{
            if(!userRepository.findById(userId).isPresent()){
                LOGGER.info("Not found User with id: %s" , userId);
                throw new DataNotFoundException(ErrorCode.ERR_USER_NOT_FOUND);
            }
            Cart cart = cartRepository.findByUserId(userId).get();
            if(!checkItemExistInCart(cart , productId)){
                throw new DataNotFoundException(ErrorCode.ERR_ITEM_CART_NOT_FOUND);
            }
            //check quantity of item
            CartItem existedItem = cart.getCartItems().stream().filter(item -> item.getProduct().getId() == productId)
                    .collect(Collectors.toList()).get(0);
            existedItem.setCart(null);
            cart.getCartItems().removeIf(item -> item.getProduct().getId() == productId);
            cartItemRepository.deleteById(existedItem.getId());
            cartRepository.save(cart);
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_REMOVE_ITEM_CART_FAIL);
        }

        return true;
    }

    @Override
    public boolean update(Cart cart) {

        if(null != cartRepository.save(cart)){
            return true;
         } else {
            return false;
        }
    }

    /**
     *
     * @param cart
     * @param productId
     * @return
     */
    private boolean checkItemExistInCart(Cart cart , Long productId){
        return cart.getCartItems().stream().anyMatch(item -> item.getProduct().getId() == productId);
    }
}
