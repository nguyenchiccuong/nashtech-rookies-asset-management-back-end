package com.nashtech.rootkies.dto.category.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SearchCategoryDTO {

    private String text;
    private int size;
    private int page;
    private String sortBy;
}
