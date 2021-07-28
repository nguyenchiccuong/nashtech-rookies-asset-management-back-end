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




}
