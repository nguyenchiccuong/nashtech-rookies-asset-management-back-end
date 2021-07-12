package com.nashtech.rootkies.repository;


import com.nashtech.rootkies.model.Brand;
import com.nashtech.rootkies.model.Category;
import com.nashtech.rootkies.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    BrandRepository brandRepository;

    @Test
    public void testCreateProductSuccess(){

        Category mobile = categoryRepository.findByName("Mobile").get();
        Brand apple = brandRepository.findByName("Apple").get();

        Product iphone11pmax = new Product();
        iphone11pmax.setContent("this is content in product iphone 11 pro max");
        iphone11pmax.setCreatedDate(LocalDateTime.now());
        iphone11pmax.setDescription("iphone 11 pro max description");
        iphone11pmax.setDiscountPrice(700);
        iphone11pmax.setOriginalPrice(800);
        iphone11pmax.setDeleted(false);
        iphone11pmax.setCategory(mobile);
        iphone11pmax.setInStock(true);
        iphone11pmax.setQuantity(11);
        iphone11pmax.setBrand(apple);
        assertNotNull(productRepository.save(iphone11pmax));
    }

}
