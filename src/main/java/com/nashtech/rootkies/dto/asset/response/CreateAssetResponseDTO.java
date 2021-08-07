package com.nashtech.rootkies.dto.asset.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAssetResponseDTO {
    private String assetCode;

    private String assetName;

    private Short state;

    private LocalDateTime installDate;

    private LocationDTO location;

    private String specification;

    private CategoryDTO category;
}
