package com.nashtech.rootkies.dto.user;

import com.nashtech.rootkies.dto.user.request.RoleDTO;
import com.nashtech.rootkies.model.Role;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private Set<RoleDTO> roles = new HashSet<>();
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private boolean isDeleted;
    private String status;
}
