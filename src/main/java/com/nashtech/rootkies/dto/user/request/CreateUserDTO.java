package com.nashtech.rootkies.dto.user.request;

import com.nashtech.rootkies.enums.Gender;
import com.nashtech.rootkies.model.Location;
import com.nashtech.rootkies.model.Role;
import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserDTO {
    private String firstName;

    private String lastName;

    private LocalDateTime dateOfBirth;

    private String gender;

    private LocalDateTime joinedDate;

    private String role;

    private Long location;
}
