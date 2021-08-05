
package com.nashtech.rootkies.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.constants.SuccessCode;
import com.nashtech.rootkies.converter.AssetConverter;
import com.nashtech.rootkies.dto.asset.reponse.DetailAssetDTO;
import com.nashtech.rootkies.dto.asset.reponse.NumberOfAssetDTO;
import com.nashtech.rootkies.dto.asset.reponse.ViewAssetDTO;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.model.Asset;
import com.nashtech.rootkies.repository.AssetRepository;
import com.nashtech.rootkies.service.AssetService;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    private final AssetConverter assetConverter;

    @Autowired
    public AssetServiceImpl(AssetRepository assetRepository, AssetConverter assetConverter) {
        this.assetRepository = assetRepository;
        this.assetConverter = assetConverter;
    }

    @Override
    public ResponseDTO countAsset(Long locationId) throws DataNotFoundException {
        try {
            ResponseDTO responseDto = new ResponseDTO();
            NumberOfAssetDTO numberOfAssetDto = new NumberOfAssetDTO();
            try {
                numberOfAssetDto.setNumberOfEntity(assetRepository.CountAllByLocationAndDefaultState(locationId));
            } catch (Exception e) {
                throw new DataNotFoundException(ErrorCode.ERR_COUNT_ASSET_FAIL);
            }

            responseDto.setData(numberOfAssetDto);
            responseDto.setSuccessCode(SuccessCode.ASSET_COUNT_SUCCESS);
            return responseDto;
        } catch (Exception e) {
            throw new DataNotFoundException(ErrorCode.ERR_COUNT_ASSET_FAIL);
        }
    }

    @Override
    public ResponseDTO retrieveAsset(Pageable page, Long locationId) throws DataNotFoundException {
        try {
            ResponseDTO responseDto = new ResponseDTO();
            Page<Asset> assets;
            try {
                assets = assetRepository.getAllByLocationAndDefaultState(page, locationId);
            } catch (Exception e) {
                throw new DataNotFoundException(ErrorCode.ERR_RETRIEVE_ASSET_FAIL);
            }
            List<ViewAssetDTO> viewAssetsDTO = assetConverter.convertToListDTO(assets);

            responseDto.setData(viewAssetsDTO);
            responseDto.setSuccessCode(SuccessCode.ASSET_LOADED_SUCCESS);
            return responseDto;
        } catch (Exception e) {
            throw new DataNotFoundException(ErrorCode.ERR_RETRIEVE_ASSET_FAIL);
        }
    }

    @Override
    public ResponseDTO retrieveAssetByAssetCode(Long locationId, String assetCode) throws DataNotFoundException {
        try {
            ResponseDTO responseDto = new ResponseDTO();
            Asset asset;
            try {
                asset = assetRepository.findByAssetCode(locationId, assetCode)
                        .orElseThrow(() -> new DataNotFoundException(ErrorCode.ERR_ASSETCODE_NOT_FOUND));
            } catch (Exception e) {
                throw new DataNotFoundException(ErrorCode.ERR_ASSETCODE_NOT_FOUND);
            }
            DetailAssetDTO detailAssetDTO = assetConverter.convertToDetailDTO(asset);

            responseDto.setData(detailAssetDTO);
            responseDto.setSuccessCode(SuccessCode.ASSET_LOADED_SUCCESS);
            return responseDto;
        } catch (Exception e) {
            throw new DataNotFoundException(ErrorCode.ERR_ASSETCODE_NOT_FOUND);
        }
    }

    @Override
    public ResponseDTO countAssetHavingFilterSearchSort() {

        return null;
    }

    @Override
    public ResponseDTO retrieveAssetHavingFilterSearchSort() {
        try {
            ResponseDTO responseDto = new ResponseDTO();
            Asset asset;
            try {
                asset = assetRepository.findByAssetCode(locationId, assetCode)
                        .orElseThrow(() -> new DataNotFoundException(ErrorCode.ERR_ASSETCODE_NOT_FOUND));
            } catch (Exception e) {
                throw new DataNotFoundException(ErrorCode.ERR_ASSETCODE_NOT_FOUND);
            }
            DetailAssetDTO detailAssetDTO = assetConverter.convertToDetailDTO(asset);

            responseDto.setData(detailAssetDTO);
            responseDto.setSuccessCode(SuccessCode.ASSET_LOADED_SUCCESS);
            return responseDto;
        } catch (Exception e) {
            throw new DataNotFoundException(ErrorCode.ERR_ASSETCODE_NOT_FOUND);
        }
    }

}
