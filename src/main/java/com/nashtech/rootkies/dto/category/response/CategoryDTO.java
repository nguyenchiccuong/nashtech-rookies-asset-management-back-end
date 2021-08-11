package com.nashtech.rootkies.dto.category.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CategoryDTO {

    private Long id;

    private String name;

    private Long parentId;

    private List<CategoryDTO> subCategories;

    private String description;
}
