package com.nashtech.rootkies.dto.asset;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssetInAssignmentDTO {
    String code;
    String name;
    String category;
}
