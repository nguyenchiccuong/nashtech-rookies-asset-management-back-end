package com.nashtech.rootkies.dto.category.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryDTO {

    private Long id;

    private String name;

    private Long parentId;

    private String description;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private boolean isDeleted;
}
