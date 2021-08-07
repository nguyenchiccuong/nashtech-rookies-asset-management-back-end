package com.nashtech.rootkies.converter;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.dto.PageDTO;
import com.nashtech.rootkies.dto.auth.SignupDTO;
import com.nashtech.rootkies.dto.user.UserDTO;
import com.nashtech.rootkies.dto.user.UserDetailDTO;
import com.nashtech.rootkies.dto.user.request.CreateUserDTO;
import com.nashtech.rootkies.dto.user.request.RoleDTO;
import com.nashtech.rootkies.enums.UserStatus;
import com.nashtech.rootkies.exception.ConvertEntityDTOException;
import com.nashtech.rootkies.exception.ConverterUserException;
import com.nashtech.rootkies.model.User;
import com.nashtech.rootkies.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public UserDetailDTO entityToDetailDTO(User user) {
        try {
            return UserDetailDTO.builder()
                    .staffCode(user.getStaffCode())
                    .fullName(user.getFirstName() + " " + user.getLastName())
                    .username(user.getUsername())
                    .joinedDate(user.getJoinedDate().toLocalDate())
                    .dateOfBirth(user.getDateOfBirth().toLocalDate())
                    .gender(user.getGender().toString())
                    .type(user.getRole().getRoleName().toString())
                    .location(user.getLocation().getAddress())
                    .build();
        } catch (ConverterUserException ex) {
            throw new ConverterUserException(ErrorCode.ERR_CONVERT_ENTITY_DTO_FAIL);
        }

    }

    public PageDTO pageToPageDto(Page<User> page) {

        List<UserDetailDTO> users = page.stream()
                .map(user ->  entityToDetailDTO(user))
                .collect(Collectors.toList());

        return PageDTO.builder()
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .data(users)
                .build();

    }
    /*@Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    public User convertToEntity(SignupDTO signupDto) throws ConvertEntityDTOException {
        User user;
        try{
            user = modelMapper.map(signupDto , User.class);
            user.setPassword(encoder.encode(signupDto.getPassword()));
            user.setCreatedDate(LocalDateTime.now());
            user.setDeleted(false);
            user.setStatus(UserStatus.INACTIVE.name());
        }catch(Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }

        return user;
    }

    public User convertToEntity(CreateUserDTO dto) throws ConvertEntityDTOException {
        try{
            User user = modelMapper.map(dto , User.class);
            dto.getRoles().stream().map(role -> roleRepository.findByName(role.getName()).get()).collect(Collectors.toSet());
            user.setPassword(encoder.encode(dto.getPassword()));
            user.setCreatedDate(LocalDateTime.now());
            user.setDeleted(false);
            user.setStatus(UserStatus.ACTIVE.name());
            return user;
        }catch(Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public UserDTO convertToDTO(User user) throws ConvertEntityDTOException {

        try{
            UserDTO dto = modelMapper.map(user , UserDTO.class);
            dto.setRoles(user.getRoles().stream().map(role -> modelMapper.map(role , RoleDTO.class))
                    .collect(Collectors.toSet()));
            return dto;
        }catch(Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }*/
}
