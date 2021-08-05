package com.nashtech.rootkies.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.dto.asset.reponse.DetailAssetDTO;
import com.nashtech.rootkies.dto.asset.reponse.ViewAssetDTO;
import com.nashtech.rootkies.exception.ConvertEntityDTOException;
import com.nashtech.rootkies.model.Asset;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class AssetConverter {

    @Autowired
    ModelMapper modelMapper;

    public DetailAssetDTO convertToDetailDTO(Asset asset) throws ConvertEntityDTOException {
        try {
            return modelMapper.map(asset, DetailAssetDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewAssetDTO> convertToListDTO(Page<Asset> assets) throws ConvertEntityDTOException {
        try {
            return assets.stream().map(asset -> modelMapper.map(asset, ViewAssetDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }
}