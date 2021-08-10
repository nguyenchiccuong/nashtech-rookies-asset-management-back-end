package com.nashtech.rootkies.dto.asset.reponse;

import com.nashtech.rootkies.enums.Gender;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
	private String staffCode;

	private String username;

	private String firstName;

	private String lastName;

	private LocalDateTime dateOfBirth;

	private LocalDateTime joinedDate;

	private Gender gender;

	private RoleDTO role;

}
