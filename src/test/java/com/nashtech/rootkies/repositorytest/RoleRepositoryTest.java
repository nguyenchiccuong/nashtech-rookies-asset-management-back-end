package com.nashtech.rootkies.repositorytest;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import com.nashtech.rootkies.model.Role;
import com.nashtech.rootkies.repository.RoleRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleRepositoryTest {
    
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void findByIdTest() {
        Optional<Role> role = roleRepository.findById(1L);
        assertEquals(true, role.isPresent());
    }

}
