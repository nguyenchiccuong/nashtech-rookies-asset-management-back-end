package com.nashtech.rootkies.validator;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.exception.DuplicateDataException;
import com.nashtech.rootkies.model.Cart;
import com.nashtech.rootkies.repository.CartRepository;
import com.nashtech.rootkies.repository.UserRepository;
import com.nashtech.rootkies.service.impl.CartServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CartValidator {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

    public boolean validateAddCartItem(Long userId , Long productId)
                                throws DataNotFoundException, DuplicateDataException {
        if(!userRepository.findById(userId).isPresent()){
            LOGGER.info("Not found User with id: %s" , userId);
            throw new DataNotFoundException(ErrorCode.ERR_USER_NOT_FOUND);
        }
        Optional<Cart> opCart = cartRepository.findByUserId(userId);
        if(!opCart.isPresent()){
            LOGGER.info("Not found Cart of User with id: %s " , userId);
            throw new DataNotFoundException(ErrorCode.ERR_CART_NOT_FOUND);
        }
        Cart cart = opCart.get();
        long duplicatedCount = cart.getCartItems().stream().
                filter(item -> item.getProduct().getId() == productId).count();
        if(duplicatedCount > 0){
            LOGGER.info("Duplicated product in cart with id: %s" , productId);
            throw new DuplicateDataException(ErrorCode.ERR_DUPLICATE_ITEM_CART);
        }
        return true;
    }
}
