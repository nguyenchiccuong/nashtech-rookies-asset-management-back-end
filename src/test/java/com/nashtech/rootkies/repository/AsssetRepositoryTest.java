package com.nashtech.rootkies.repository;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import com.nashtech.rootkies.model.Asset;
import com.nashtech.rootkies.model.Category;
import com.nashtech.rootkies.model.Location;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AsssetRepositoryTest {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Test
    public void AssetTest() {
        Location location = new Location();
        location.setAddress("121565");

        assertNotNull(locationRepository.save(location));

        Category category = new Category();
        category.setCategoryCode("TE");
        category.setCategoryName("test");

        assertNotNull(categoryRepository.save(category));

        locationRepository.deleteById(location.getLocationId());

        assertTrue(!locationRepository.findById(location.getLocationId()).isPresent());

        categoryRepository.deleteById("TE");

        assertTrue(!categoryRepository.findById("TE").isPresent());
    }

    @Test
    public void saveErrorTest() {
        Exception result = null;
        try {
            assetRepository.save(new Asset());
        } catch (Exception e) {
            result = e;
            System.out.println(result.getMessage());
        }

        assertNotNull(result);
    }

}
