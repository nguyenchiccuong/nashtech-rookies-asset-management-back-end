package com.nashtech.rootkies.controllers;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.converter.CartConverter;
import com.nashtech.rootkies.dto.cart.response.CartDTO;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.exception.UpdateDataFailException;
import com.nashtech.rootkies.service.CartService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/carts")
@Api( tags = "Cart")
public class CartController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CartService cartService;

    @Autowired
    CartConverter cartConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    /**
     *
     * @param id
     * @return
     * @throws DataNotFoundException
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getCart(@PathVariable("id") Long id) throws DataNotFoundException {
        ResponseDTO response = new ResponseDTO();
        try{
            response.setData(cartService.getCart(id));
        }catch(Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_CART_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    /**
     *
     * @param userId
     * @param productId
     * @return
     * @throws UpdateDataFailException
     */
    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<ResponseDTO> addItemToCart(@Valid @PathVariable Long userId,@PathVariable Long productId)
                                    throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try{
            response.setData(cartService.addItemToCart(userId , productId));
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_ADD_ITEM_CART_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    /**
     *
     * @param userId
     * @param productId
     * @return
     * @throws UpdateDataFailException
     */
    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<ResponseDTO> removeItemFromCart(@Valid @PathVariable Long userId,@PathVariable Long productId)
            throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            response.setData(cartService.removeItem(userId , productId));
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_REMOVE_ITEM_CART_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    /**
     *
     * @param cartDto
     * @return
     * @throws UpdateDataFailException
     */
    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateCart(@Valid @RequestBody CartDTO cartDto) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try{
            response.setData(cartService.update(cartConverter.convertCartDtoToEntity(cartDto)));
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_CART_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

}
