package com.nashtech.rootkies.converter;

import com.nashtech.rootkies.dto.user.UserDetailDTO;
import com.nashtech.rootkies.dto.user.request.CreateUserDTO;
import com.nashtech.rootkies.enums.ERole;
import com.nashtech.rootkies.enums.Gender;
import com.nashtech.rootkies.exception.ConvertEntityDTOException;
import com.nashtech.rootkies.model.Location;
import com.nashtech.rootkies.model.Role;
import com.nashtech.rootkies.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Test
    public void convertEntityToDetailDTO(){
        UserDetailDTO dto = UserDetailDTO.builder()
                .staffCode("SD001")
                .fullName("Nguyen Hung")
                .joinedDate(LocalDate.now())
                .dateOfBirth(LocalDate.now().minusYears(20))
                .type("ROLE_ADMIN")
                .location("HCM")
                .username("hungnb")
                .gender("Male")
                .build();

        User user = User.builder()
                .staffCode("SD001")
                .lastName("Hung")
                .firstName("Nguyen")
                .location(Location.builder().locationId(1L).address("HCM").build())
                .gender(Gender.Male)
                .role(Role.builder().id(1L).roleName(ERole.ROLE_ADMIN).build())
                .joinedDate(LocalDateTime.now())
                .dateOfBirth(LocalDateTime.now().minusYears(20))
                .username("hungnb")
                .build();

        assertEquals(userConverter.entityToDetailDTO(user).getStaffCode() , dto.getStaffCode());
        assertEquals(userConverter.entityToDetailDTO(user).getUsername() , dto.getUsername());
        assertEquals(userConverter.entityToDetailDTO(user).getType() , dto.getType());
        assertEquals(userConverter.entityToDetailDTO(user).getLocation() , dto.getLocation());
        assertEquals(userConverter.entityToDetailDTO(user).getGender() , dto.getGender());
        assertEquals(userConverter.entityToDetailDTO(user).getFullName() , dto.getFullName());
        assertEquals(userConverter.entityToDetailDTO(user).getDateOfBirth() , dto.getDateOfBirth());
        assertEquals(userConverter.entityToDetailDTO(user).getJoinedDate() , dto.getJoinedDate());
    }

}
