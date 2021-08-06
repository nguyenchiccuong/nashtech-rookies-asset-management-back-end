package com.nashtech.rootkies.converter;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.dto.auth.SignupDTO;
import com.nashtech.rootkies.dto.user.UserDTO;
import com.nashtech.rootkies.dto.user.request.CreateUserDTO;
import com.nashtech.rootkies.dto.user.request.RoleDTO;
import com.nashtech.rootkies.enums.ERole;
import com.nashtech.rootkies.enums.Gender;
import com.nashtech.rootkies.enums.UserStatus;
import com.nashtech.rootkies.exception.ConvertEntityDTOException;
import com.nashtech.rootkies.model.Role;
import com.nashtech.rootkies.model.User;
import com.nashtech.rootkies.repository.LocationRepository;
import com.nashtech.rootkies.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    LocationRepository locationRepository;

    public User convertToEntity(SignupDTO signupDto) throws ConvertEntityDTOException {
        User user;
        try{
            user = modelMapper.map(signupDto , User.class);
            /*user.setPassword(encoder.encode(signupDto.getPassword()));
            user.setCreatedDate(LocalDateTime.now());
            user.setDeleted(false);
            user.setStatus(UserStatus.INACTIVE.name());*/
        }catch(Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }

        return user;
    }

    public User convertToEntity(CreateUserDTO dto) throws ConvertEntityDTOException {
        try{
            User user = modelMapper.map(dto , User.class);
            /*dto.getRoles().stream().map(role -> roleRepository.findByName(role.getName()).get()).collect(Collectors.toSet());
            user.setPassword(encoder.encode(dto.getPassword()));
            user.setCreatedDate(LocalDateTime.now());
            user.setDeleted(false);
            user.setStatus(UserStatus.ACTIVE.name());*/
            return user;
        }catch(Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public UserDTO convertToDTO(User user) throws ConvertEntityDTOException {
        try{
            UserDTO dto = modelMapper.map(user , UserDTO.class);
            /*dto.setRoles(user.getRoles().stream().map(role -> modelMapper.map(role , RoleDTO.class))
                    .collect(Collectors.toSet()));*/
            return dto;
        }catch(Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public User convertCreateUserDTOtoEntity(CreateUserDTO createUserDTO) throws ConvertEntityDTOException {
        try{
            User user = modelMapper.map(createUserDTO , User.class);
            //gender
            user.setGender(Gender.valueOf(createUserDTO.getGender()));
            //role
            ERole eRole = ERole.valueOf(createUserDTO.getRole());
            user.setRole(roleRepository.findByRoleName(eRole).get());
            //convert string to datetime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            user.setDateOfBirth(LocalDateTime.parse(createUserDTO.getDateOfBirth(), formatter));
            user.setJoinedDate(LocalDateTime.parse(createUserDTO.getJoinedDate(), formatter));
            //location
            user.setLocation(locationRepository.findById(createUserDTO.getLocation()).get());
            return user;
        } catch(Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }
}
