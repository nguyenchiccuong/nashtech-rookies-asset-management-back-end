package com.nashtech.rootkies.repository;

import com.nashtech.rootkies.model.User;
import com.nashtech.rootkies.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositotyTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void existsByUsernameTest() {
        Optional<User> user = userRepository.findById("SD0001");
        assertNotNull(user);
        String username = user.get().getUsername();
        Boolean check = userRepository.existsByUsername(username);
        assertEquals(check, true);
    }
}
