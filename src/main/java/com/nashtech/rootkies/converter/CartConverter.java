package com.nashtech.rootkies.converter;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.dto.cart.request.CartItemDTO;
import com.nashtech.rootkies.dto.cart.response.CartDTO;
import com.nashtech.rootkies.exception.ConvertEntityException;
import com.nashtech.rootkies.model.Cart;
import com.nashtech.rootkies.model.CartItem;
import com.nashtech.rootkies.repository.CartItemRepository;
import com.nashtech.rootkies.repository.CartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class CartConverter {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository itemRepository;

    /**
     *
     * @param cart
     * @return CartDTO
     */
    public CartDTO convertCartEntityToDTO(Cart cart) throws ParseException {

        CartDTO dto = null;
        try {
            dto = modelMapper.map(cart, CartDTO.class);
            dto.setCartItems(cart.getCartItems().stream().map(item -> modelMapper.map(item , CartItemDTO.class))
                        .collect(Collectors.toList()));
            dto.setUserId(cart.getUser().getId());
        }catch(Exception ex){
            throw new ParseException(ex.getMessage() , 1);
        }
        return dto;
    }

    /**
     *
     * @param dto
     * @return
     */
    public CartItem convertItemDTOToEntity(CartItemDTO dto) throws ConvertEntityException {
        CartItem item = null;
        try{
            item = modelMapper.map(dto , CartItem.class);
        }catch(Exception ex){
            throw new ConvertEntityException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return item;
    }

    /**
     *
     * @param cartDto
     * @return
     */
    public Cart convertCartDtoToEntity(CartDTO cartDto) {
        Cart cart = cartRepository.findById(cartDto.getId()).get();
        cart.setCartItems(cartDto.getCartItems().stream().map(item -> modelMapper.map(item , CartItem.class)).collect(Collectors.toList()));
        cart.setUpdatedDate(LocalDateTime.now());
        return cart;
    }
}
