package com.nashtech.rootkies.converter;

import com.nashtech.rootkies.dto.user.request.CreateUserDTO;
import com.nashtech.rootkies.exception.ConvertEntityDTOException;
import com.nashtech.rootkies.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserConverterTest {
    @Autowired
    UserConverter userConverter;

    @Test
    public void convertCreateUserDTOtoEntityTest() throws ConvertEntityDTOException {
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setFirstName("Nhi");
        createUserDTO.setLastName("Mai Hoang");
        createUserDTO.setGender("Female");
        createUserDTO.setLocation(1L);
        createUserDTO.setDateOfBirth("2000-06-01 00:00");
        createUserDTO.setJoinedDate("2021-08-10 00:00");
        createUserDTO.setRole("ROLE_USER");

        User user = userConverter.convertCreateUserDTOtoEntity(createUserDTO);

        assertNotNull(user);
        assertEquals(createUserDTO.getGender(), user.getGender().name());
        assertEquals(createUserDTO.getLastName(), user.getLastName());
        assertEquals(createUserDTO.getFirstName(), user.getFirstName());
        assertEquals(createUserDTO.getLocation(), user.getLocation().getLocationId());
    }

}
