package com.nashtech.rootkies.repository;

import com.nashtech.rootkies.model.Address;
import com.nashtech.rootkies.model.Brand;
import com.nashtech.rootkies.model.Category;
import com.nashtech.rootkies.model.Organization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BrandRepositoryTest {

    @Autowired
    BrandRepository brandRepository;
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void testCreateBrandSuccess(){
        Brand apple = new Brand();
        apple.setName("Apple");
        Organization appleCorp = organizationRepository.findByName("Apple Corp").get();
        apple.setOrganization(appleCorp);
        Set<Category> cates = new HashSet<Category>();
        cates.add(categoryRepository.findByName("Mobile").get());
        apple.setCategories(cates);
        brandRepository.save(apple);
    }
}
