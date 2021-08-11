package com.nashtech.rootkies.service;

import com.nashtech.rootkies.enums.ERole;
import com.nashtech.rootkies.enums.Gender;
import com.nashtech.rootkies.exception.CreateDataFailException;
import com.nashtech.rootkies.exception.ResourceNotFoundException;
import com.nashtech.rootkies.exception.UserNotFoundException;
import com.nashtech.rootkies.model.Location;
import com.nashtech.rootkies.model.Role;
import com.nashtech.rootkies.model.User;
import com.nashtech.rootkies.repository.LocationRepository;
import com.nashtech.rootkies.repository.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void createUserTest() throws CreateDataFailException {
        User user = new User();
        user.setFirstName("Nhi");
        user.setLastName("Mai Hoang");
        user.setGender(Gender.Female);
        user.setJoinedDate(LocalDateTime.now());
        user.setDateOfBirth(LocalDateTime.of(2000, 6, 1, 0,0,0));

        Location location = locationRepository.findById(1L).get();
        user.setLocation(location);
        Role role = roleRepository.findByRoleName(ERole.ROLE_USER).get();
        user.setRole(role);

        Boolean createUser = userService.createUser(user);
        assertNotNull(createUser);
    }

    @Test
    public void UpdateUserTest() throws UserNotFoundException, ResourceNotFoundException {
        User user = new User();
        user.setFirstName("Nhi");
        user.setLastName("Mai Hoang");
        user.setGender(Gender.Female);
        user.setJoinedDate(LocalDateTime.now());
        user.setDateOfBirth(LocalDateTime.of(2000, 6, 1, 0,0,0));
        Role role = roleRepository.findByRoleName(ERole.ROLE_USER).get();
        user.setRole(role);

        User updateUser = userService.updateUser("SD0002", user);
        assertEquals("SD0002", updateUser.getStaffCode());
        assertEquals(user.getDateOfBirth(), updateUser.getDateOfBirth());
        assertEquals(user.getGender(), updateUser.getGender());
        assertEquals(user.getJoinedDate(), updateUser.getJoinedDate());
        assertEquals(user.getRole().getRoleName(), updateUser.getRole().getRoleName());
    }
}
