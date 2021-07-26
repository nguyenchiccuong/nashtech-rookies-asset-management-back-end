package com.nashtech.rootkies.dto.organization.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrganizationDTO {

    @NotEmpty
    private String name;
    @NotEmpty
    private String country;
    private String state;
    @NotEmpty
    private String city;
    @NotEmpty
    private String district;
    @NotEmpty
    private String ward;
    @NotEmpty
    private String street;


}
