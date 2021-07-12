package com.nashtech.rootkies.dto.user.request;

import com.nashtech.rootkies.model.ERole;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    private ERole name;
}
