package com.nashtech.rootkies.repository;

import com.nashtech.rootkies.enums.UserStatus;
import com.nashtech.rootkies.model.Address;
import com.nashtech.rootkies.model.ERole;
import com.nashtech.rootkies.model.Role;
import com.nashtech.rootkies.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void testCreateUserSuccess(){
        User user = new User();
        user.setUsername("leonguyencm1");
        user.setEmail("leonguyencm1@gmail.com");
        user.setPhone("0908909646");
        user.setPassword("Aa1234qwer");
        user.setStatus(UserStatus.ACTIVE.name());
        Set<Role> setRoles = new HashSet<>();
        setRoles.add(roleRepository.findByName(ERole.ROLE_SUPER_ADMINISTRATOR).get());
        user.setRoles(setRoles);
        assertNotNull(userRepository.save(user));
    }

    @Test
    public void testInitDefaultRolesSuccess(){
        Role superadmin = new Role();
        superadmin.setName(ERole.ROLE_SUPER_ADMINISTRATOR);
        assertNotNull(roleRepository.save(superadmin));

        Role admin = new Role();
        admin.setName(ERole.ROLE_ADMINISTRATOR);
        assertNotNull(roleRepository.save(admin));

        Role roleUser = new Role();
        roleUser.setName(ERole.ROLE_USER);
        assertNotNull(roleRepository.save(roleUser));
    }

    @Test
    public void testCreateUserAddress(){
        User user =userRepository.findByUsername("anhnguyen").get();
        Address address1 = new Address();
        address1.setStreetAddress("phan huy ich");
        address1.setCity("ho chi minh");
        address1.setCountry("viet nam");
        address1.setDistrict("go vap");
        address1.setWard("ward 5");
        address1.setState(null);
        address1.setDeleted(false);
        user.setAddresses(Arrays.asList(address1));
        userRepository.save(user);
    }
}
