package com.nashtech.rootkies.dto.auth;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
