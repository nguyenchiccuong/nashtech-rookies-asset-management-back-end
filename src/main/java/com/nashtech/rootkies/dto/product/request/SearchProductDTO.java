package com.nashtech.rootkies.dto.product.request;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchProductDTO {

    private int page;
    private int size;
    private String text;
    private String sortBy;
}
