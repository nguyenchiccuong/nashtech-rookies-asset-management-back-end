package com.nashtech.rootkies.repository;

import com.nashtech.rootkies.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
