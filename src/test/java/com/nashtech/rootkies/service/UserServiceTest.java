package com.nashtech.rootkies.service;

import com.nashtech.rootkies.exception.UpdateDataFailException;
import com.nashtech.rootkies.model.User;
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

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    UserService userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


}
