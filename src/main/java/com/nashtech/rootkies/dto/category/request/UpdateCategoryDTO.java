package com.nashtech.rootkies.dto.category.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateCategoryDTO {
    @NotBlank
    private Long id;
    @NotBlank
    private String name;

    private Long parentId;
    private String description;
}
