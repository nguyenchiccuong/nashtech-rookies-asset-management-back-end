package com.nashtech.rootkies.dto.brand.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class CreateBrandDTO {

    private String name;
    private List<Long> categories = new ArrayList<>();
    private Long organizationId;

}
