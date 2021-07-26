package com.nashtech.rootkies.service;

import com.nashtech.rootkies.dto.auth.JwtResponse;
import com.nashtech.rootkies.dto.auth.LoginDTO;
import com.nashtech.rootkies.dto.auth.SignupDTO;
import com.nashtech.rootkies.exception.UserAuthenticationException;
import com.nashtech.rootkies.exception.UserNotFoundException;
import com.nashtech.rootkies.exception.UserSignupException;
import com.nashtech.rootkies.model.ERole;
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

import java.util.Arrays;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthServiceTest {

    @Mock
    AuthService authService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSignup() throws UserNotFoundException, UserSignupException {
        User user = new User("nguyenna", "nguyenna@gmail.com" , "Aa1234qwer");
        SignupDTO signupDto = new SignupDTO("nguyenna", "nguyenna@gmail.com" , "Aa1234qwer");
        Mockito.when(authService.signup(signupDto)).thenReturn(true);
        Assert.assertEquals(authService.signup(signupDto) , true);
    }

    @Test
    public void testSignIn() throws UserAuthenticationException {
        LoginDTO loginDto = new LoginDTO("nguyennna" , "Aa1234qwer");
        JwtResponse jwtResponse = new JwtResponse("Aa1234!@#$qwer)(*&0987poiu" , "Bearer" ,1L ,
                "nguyenna" , "nguyenna@gmail.com" , Arrays.asList(ERole.ROLE_USER.name()));
        Mockito.when(authService.signin(loginDto)).thenReturn(jwtResponse);
        Assert.assertEquals(jwtResponse , authService.signin(loginDto));
    }
}
