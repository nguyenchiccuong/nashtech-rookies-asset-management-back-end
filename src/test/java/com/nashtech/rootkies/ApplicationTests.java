package com.nashtech.rootkies;

import com.nashtech.rootkies.service.CategoryService;
import com.nashtech.rootkies.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("test")
public class ApplicationTests {

	@Bean
	@Primary
	public CategoryService categoryService(){
		return Mockito.mock(CategoryService.class);
	}

	@Bean
	@Primary
	public UserService userService(){
		return Mockito.mock(UserService.class);
	}

	@Test
	public void contextLoads() {
	}

}
