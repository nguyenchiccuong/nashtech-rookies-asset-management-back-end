package com.nashtech.rootkies.dto.category.request;

import lombok.Data;

@Data
public class SearchCategoryDTO {
    private String text;
    private int size;
    private int sizePerPage;
    private String sortOrder;
}
