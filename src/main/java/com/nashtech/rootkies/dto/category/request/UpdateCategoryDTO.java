package com.nashtech.rootkies.dto.category.request;

import lombok.Data;

@Data
public class UpdateCategoryDTO {
    private Long id;
    private String name;
    private Long parentId;
    private String description;
}
