package com.nashtech.rootkies.dto.user.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordRequest {
    private String staffCode;
    private String newPassword;
}
