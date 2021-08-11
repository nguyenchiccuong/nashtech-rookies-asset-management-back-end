package com.nashtech.rootkies.dto.category.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCategoryDTO {

    private String name;
    private Long parentId;
    private String description;

}
