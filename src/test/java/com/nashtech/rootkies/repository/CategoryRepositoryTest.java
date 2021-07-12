package com.nashtech.rootkies.repository;

import com.nashtech.rootkies.model.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void testCreateNewCategorySuccess(){
        Category mobile = new Category();
        mobile.setName("Mobile");
        mobile.setDescription("This category contains mobile phones");
        mobile.setCreatedDate(LocalDateTime.now());
        mobile.setUpdatedDate(LocalDateTime.now());
        mobile.setDeleted(false);
        assertNotNull(categoryRepository.save(mobile));
    }

    @Test
    public void testCreateParentAndSubCategoriesSuccess(){
        Category electronic = new Category();
        electronic.setName("Electronic Devices");
        Set<Category> subCategories = new HashSet<>();
        Category mobile = categoryRepository.findByName("Mobile").get();

        subCategories.add(mobile);
        electronic.setSubCategories(subCategories);
        assertNotNull(categoryRepository.save(electronic));

        mobile.setParent(categoryRepository.findByName("Electronic Devices").get());
        assertNotNull(categoryRepository.save(mobile));
    }
}
