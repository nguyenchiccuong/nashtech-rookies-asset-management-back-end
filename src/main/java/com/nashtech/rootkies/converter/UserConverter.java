package com.nashtech.rootkies.converter;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.dto.auth.SignupDTO;
import com.nashtech.rootkies.dto.user.UserDTO;
import com.nashtech.rootkies.dto.user.request.CreateUserDTO;
import com.nashtech.rootkies.dto.user.request.EditUserDTO;
import com.nashtech.rootkies.enums.ERole;
import com.nashtech.rootkies.enums.Gender;
import com.nashtech.rootkies.exception.ConvertEntityDTOException;
import com.nashtech.rootkies.model.User;
import com.nashtech.rootkies.repository.LocationRepository;
import com.nashtech.rootkies.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    public UserDTO convertToDto(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        //gender
        userDTO.setGender(user.getGender().name());
        //date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        userDTO.setDateOfBirth(user.getDateOfBirth().format(formatter));
        userDTO.setJoinedDate(user.getJoinedDate().format(formatter));
        //role
        userDTO.setRole(user.getRole().getRoleName().name());
        return userDTO;
    }

    public User convertToEti(UserDTO userDTO) {
        User user = modelMapper.map(userDTO , User.class);
        //gender
        user.setGender(Gender.valueOf(userDTO.getGender()));
        //role
        ERole eRole = ERole.valueOf(userDTO.getRole());
        user.setRole(roleRepository.findByRoleName(eRole).get());
        //convert string to datetime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        user.setDateOfBirth(LocalDateTime.parse(userDTO.getDateOfBirth(), formatter));
        user.setJoinedDate(LocalDateTime.parse(userDTO.getJoinedDate(), formatter));
        //location
        user.setLocation(locationRepository.findById(userDTO.getLocation()).get());
        return user;
    }


    public List<UserDTO> toListDto(List<User> listEntity) {
        List<UserDTO> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.convertToDto(e));
        });
        return listDto;
    }

    public User convertEditUserDTOtoEntity(EditUserDTO editUserDTO) throws ConvertEntityDTOException {
        try{
            User user = modelMapper.map(editUserDTO , User.class);
            //gender
            user.setGender(Gender.valueOf(editUserDTO.getGender()));
            //role
            ERole eRole = ERole.valueOf(editUserDTO.getRole());
            user.setRole(roleRepository.findByRoleName(eRole).get());
            //convert string to datetime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            user.setDateOfBirth(LocalDateTime.parse(editUserDTO.getDateOfBirth(), formatter));
            user.setJoinedDate(LocalDateTime.parse(editUserDTO.getJoinedDate(), formatter));
            return user;
        } catch(Exception ex){
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
