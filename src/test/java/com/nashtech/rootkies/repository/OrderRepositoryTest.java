package com.nashtech.rootkies.repository;

import com.nashtech.rootkies.enums.OrderStatus;
import com.nashtech.rootkies.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @Test
    public void testCreateOrderSuccess(){

        User user =userRepository.findByUsername("anhnguyen").get();
        Order order = new Order();
        order.setCode("OD001");
        order.setStatus(OrderStatus.SUCCESS.name());
        order.setUser(user);

        Product iphone = productRepository.findById(56L).get();
        Set<OrderedItem> orderedItems = new HashSet<>();
        OrderedItem item1 = new OrderedItem();
        item1.setProduct(iphone);
        item1.setOrder(order);
        Address address = new Address();
        address.setCountry("vn");
        address.setCity("ho chi minh");
        address.setDistrict("go vap");
        address.setStreetAddress("quang trung");
        address.setWard("ward 10");
        order.setDeliveryPlace(address);
        orderedItems.add(item1);
        order.setOrderedItems(orderedItems);
        order.setStatus(OrderStatus.WAITING.name());
        assertNotNull(orderRepository.save(order));
    }
}
