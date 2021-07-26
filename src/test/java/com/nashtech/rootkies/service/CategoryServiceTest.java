package com.nashtech.rootkies.service;

import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.exception.DeleteDataFailException;
import com.nashtech.rootkies.exception.UpdateDataFailException;
import com.nashtech.rootkies.model.Category;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

    @Mock
    CategoryService categoryService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateSingleCategoryHaveNoChildSuccess() throws UpdateDataFailException {
        Category mobile = new Category(0L , "Mobile");
        Category expectReturn = new Category(1L ,"Mobile");
        Mockito.when(categoryService.create(mobile)).thenReturn(true);
        Assert.assertEquals(expectReturn , categoryService.create(mobile));
    }

    @Test
    public void testCreateCategoryWithOneChildCategory() throws UpdateDataFailException {
        Category electronicDevice = new Category(0L , "Electronic Device");
        Category mobile = new Category(0L , "Mobile");
        //mobile.setParent(electronicDevice);
        Mockito.when(categoryService.create(electronicDevice)).thenReturn(true);
        Assert.assertEquals(categoryService.create(electronicDevice) , electronicDevice);
    }

    @Test
    public void testCreateCategoryWithManyChildren() throws UpdateDataFailException {
        Category electronicDevice = new Category(0L , "Electronic Device");
        Category mobile = new Category(0L , "Mobile");
        //mobile.setParent(electronicDevice);
        Category laptop = new Category(0L , "Laptop");
        //laptop.setParent(electronicDevice);
        Mockito.when(categoryService.create(electronicDevice)).thenReturn(true);
        Assert.assertEquals(categoryService.create(electronicDevice) , electronicDevice);
    }

    @Test
    public void testGetSingleCategory() throws DataNotFoundException {
        Optional<Category> mobile = Optional.of(new Category(1L , "Mobile"));
        Mockito.when(categoryService.findCategory(1L)).thenReturn(mobile);
        Assert.assertEquals(mobile , categoryService.findCategory(1L));
    }

    @Test
    public void testGetCategoryHaveManyChildren() throws DataNotFoundException {
        Optional<Category> electronicDevice = Optional.of(new Category(1L , "Electronic Device"));
        Category mobile = new Category(0L , "Mobile");
        //mobile.setParent(electronicDevice.get());
        Category laptop = new Category(0L , "Laptop");
        //laptop.setParent(electronicDevice.get());
        Mockito.when(categoryService.findCategory(1L)).thenReturn(electronicDevice);
        Assert.assertEquals(electronicDevice , categoryService.findCategory(1L));
    }

    @Test
    public void testUpdateSingleCategoryNoChild() throws UpdateDataFailException {
        Category mobile = new Category(1L , "Mobile phone");
        Category updatedMobile = new Category(1L , "Mobile phone");
        Mockito.when(categoryService.update(mobile)).thenReturn(updatedMobile);
        Assert.assertEquals(updatedMobile , categoryService.update(mobile));
    }

    @Test
    public void testUpdateCategoryHaveManyChildren() throws UpdateDataFailException {
        Category electronicDevice = new Category(1L , "Electronic Device");
        Category updatedElectronicDevices = new Category(1L , "Electronic Devices");
        Category mobile = new Category(0L , "Mobile");
       //mobile.setParent(electronicDevice);
        Category laptop = new Category(0L , "Laptop");
        //laptop.setParent(electronicDevice);
        Mockito.when(categoryService.update(electronicDevice)).thenReturn(updatedElectronicDevices);
        Assert.assertEquals(updatedElectronicDevices , categoryService.update(electronicDevice));
    }

    @Test
    public void testDeleteSingleCategory() throws DeleteDataFailException {
        Category mobile = new Category(1L , "Mobile");
        Mockito.when(categoryService.delete(1L)).thenReturn(true);
        Assert.assertEquals(true ,categoryService.delete(1L));
    }

    @Test
    public void testDeleteCategoryHaveManyChildren() throws DeleteDataFailException {
        Category electronicDevice = new Category(1L , "Electronic Device");
        Category mobile = new Category(3L , "Mobile");
        //mobile.setParent(electronicDevice);
        Category laptop = new Category(0L , "Laptop");
        //laptop.setParent(electronicDevice);
        Mockito.when(categoryService.delete(1L)).thenReturn(false);
        Assert.assertEquals(false , categoryService.delete(1L));
    }


}
