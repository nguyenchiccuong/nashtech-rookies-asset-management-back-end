package com.nashtech.rootkies.repository;

import com.nashtech.rootkies.model.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository  extends CrudRepository<CartItem, Long> {

    void deleteByProductId(Long productId);

}
