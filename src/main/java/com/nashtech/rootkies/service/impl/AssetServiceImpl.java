
package com.nashtech.rootkies.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.constants.SuccessCode;
import com.nashtech.rootkies.converter.AssetConverter;
import com.nashtech.rootkies.dto.asset.reponse.DetailAssetDTO;
import com.nashtech.rootkies.dto.asset.reponse.NumberOfAssetDTO;
import com.nashtech.rootkies.dto.asset.reponse.ViewAssetDTO;
import com.nashtech.rootkies.dto.asset.request.SearchFilterSortAssetDTO;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.enums.SortType;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.model.Asset;
import com.nashtech.rootkies.repository.AssetRepository;
import com.nashtech.rootkies.repository.specs.AssetSpecification;
import com.nashtech.rootkies.repository.specs.SearchCriteria;
import com.nashtech.rootkies.repository.specs.SearchOperation;
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
    public ResponseDTO retrieveAssetHavingFilterSearchSort(Integer pageNum, Integer numOfItems,
            SearchFilterSortAssetDTO searchFilterSortAssetDTO, Long locationId) throws DataNotFoundException {
        try {
            ResponseDTO responseDto = new ResponseDTO();
            Pageable page;
            if (!(searchFilterSortAssetDTO.getSortField().isBlank()
                    || searchFilterSortAssetDTO.getSortType().isBlank())) {
                if (searchFilterSortAssetDTO.getSortField().equalsIgnoreCase("category")) {
                    if (searchFilterSortAssetDTO.getSortType().equalsIgnoreCase(SortType.DSC.toString())) {
                        page = PageRequest.of(pageNum, numOfItems, Sort.by("category.categoryName").descending());
                    } else {
                        page = PageRequest.of(pageNum, numOfItems, Sort.by("category.categoryName").ascending());
                    }
                } else {
                    if (searchFilterSortAssetDTO.getSortType().equalsIgnoreCase(SortType.DSC.toString())) {
                        page = PageRequest.of(pageNum, numOfItems,
                                Sort.by(searchFilterSortAssetDTO.getSortField()).descending());
                    } else {
                        page = PageRequest.of(pageNum, numOfItems,
                                Sort.by(searchFilterSortAssetDTO.getSortField()).ascending());
                    }
                }

            } else {
                page = PageRequest.of(pageNum, numOfItems, Sort.by("assetName").ascending());
            }

            AssetSpecification assetState = null;
            AssetSpecification assetCategoryCode = null;
            AssetSpecification assetCode = null;
            AssetSpecification assetName = null;
            AssetSpecification assetLocation = new AssetSpecification();
            assetLocation.add(new SearchCriteria("location", locationId, SearchOperation.EQUAL));
            AssetSpecification assetIsDeleted = new AssetSpecification();
            assetIsDeleted.add(new SearchCriteria("isDeleted", false, SearchOperation.EQUAL));

            if (!searchFilterSortAssetDTO.getStates().isEmpty()) {
                assetState = new AssetSpecification();
                assetState.add(new SearchCriteria("state", searchFilterSortAssetDTO.getStates(), SearchOperation.IN));
            }
            if (!searchFilterSortAssetDTO.getCategoriesCode().isEmpty()) {
                assetCategoryCode = new AssetSpecification();
                assetCategoryCode.add(new SearchCriteria("category", searchFilterSortAssetDTO.getCategoriesCode(),
                        SearchOperation.IN));
            }
            if (!searchFilterSortAssetDTO.getSearchKeyWord().isBlank()) {
                assetCode = new AssetSpecification();
                assetCode.add(new SearchCriteria("assetCode", searchFilterSortAssetDTO.getSearchKeyWord(),
                        SearchOperation.MATCH));

                assetName = new AssetSpecification();
                assetName.add(new SearchCriteria("assetName", searchFilterSortAssetDTO.getSearchKeyWord(),
                        SearchOperation.MATCH));
            }

            Specification spec = Specification.where(assetLocation).and(assetIsDeleted);

            if (assetState != null) {
                spec = spec.and(assetState);
            }
            if (assetCategoryCode != null) {
                spec = spec.and(assetCategoryCode);
            }
            if (assetCode != null) {
                spec = spec.and(assetCode);
                spec = spec.or(assetName);
            }

            Page<Asset> assets;
            try {
                assets = assetRepository.findAll(spec, page);
            } catch (Exception e) {
                e.printStackTrace();
                throw new DataNotFoundException(ErrorCode.ERR_RETRIEVE_ASSET_FAIL);
            }
            List<ViewAssetDTO> viewAssetsDTO = assetConverter.convertToListDTO(assets);

            if (viewAssetsDTO.size() <= 0) {
                spec = Specification.where(assetLocation).and(assetIsDeleted);

                if (assetState != null) {
                    spec = spec.and(assetState);
                }
                if (assetCategoryCode != null) {
                    spec = spec.and(assetCategoryCode);
                }
                if (assetCode != null) {
                    spec = spec.and(assetName);
                    spec = spec.or(assetCode);
                }
                try {
                    assets = assetRepository.findAll(spec, page);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new DataNotFoundException(ErrorCode.ERR_RETRIEVE_ASSET_FAIL);
                }

                viewAssetsDTO = assetConverter.convertToListDTO(assets);
            }

            responseDto.setData(viewAssetsDTO);
            responseDto.setSuccessCode(SuccessCode.ASSET_LOADED_SUCCESS);
            return responseDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataNotFoundException(ErrorCode.ERR_RETRIEVE_ASSET_FAIL);
        }
    }

    @Override
    public ResponseDTO countAssetHavingFilterSearchSort(SearchFilterSortAssetDTO searchFilterSortAssetDTO,
            Long locationId) throws DataNotFoundException {
        try {
            ResponseDTO responseDto = new ResponseDTO();

            AssetSpecification assetState = null;
            AssetSpecification assetCategoryCode = null;
            AssetSpecification assetCode = null;
            AssetSpecification assetName = null;
            AssetSpecification assetLocation = new AssetSpecification();
            assetLocation.add(new SearchCriteria("location", locationId, SearchOperation.EQUAL));
            AssetSpecification assetIsDeleted = new AssetSpecification();
            assetIsDeleted.add(new SearchCriteria("isDeleted", false, SearchOperation.EQUAL));

            if (!searchFilterSortAssetDTO.getStates().isEmpty()) {
                assetState = new AssetSpecification();
                assetState.add(new SearchCriteria("state", searchFilterSortAssetDTO.getStates(), SearchOperation.IN));
            }
            if (!searchFilterSortAssetDTO.getCategoriesCode().isEmpty()) {
                assetCategoryCode = new AssetSpecification();
                assetCategoryCode.add(new SearchCriteria("category", searchFilterSortAssetDTO.getCategoriesCode(),
                        SearchOperation.IN));
            }
            if (!searchFilterSortAssetDTO.getSearchKeyWord().isBlank()) {
                assetCode = new AssetSpecification();
                assetCode.add(new SearchCriteria("assetCode", searchFilterSortAssetDTO.getSearchKeyWord(),
                        SearchOperation.MATCH));

                assetName = new AssetSpecification();
                assetName.add(new SearchCriteria("assetName", searchFilterSortAssetDTO.getSearchKeyWord(),
                        SearchOperation.MATCH));
            }

            Specification spec = Specification.where(assetLocation).and(assetIsDeleted);

            if (assetState != null) {
                spec = spec.and(assetState);
            }
            if (assetCategoryCode != null) {
                spec = spec.and(assetCategoryCode);
            }
            if (assetCode != null) {
                spec = spec.and(assetCode);
                spec = spec.or(assetName);
            }

            List<Asset> assets;
            try {
                assets = assetRepository.findAll(spec);
            } catch (Exception e) {
                e.printStackTrace();
                throw new DataNotFoundException(ErrorCode.ERR_RETRIEVE_ASSET_FAIL);
            }
            List<ViewAssetDTO> viewAssetsDTO = assetConverter.convertToListDTO(assets);

            if (viewAssetsDTO.size() <= 0) {
                spec = Specification.where(assetLocation).and(assetIsDeleted);

                if (assetState != null) {
                    spec = spec.and(assetState);
                }
                if (assetCategoryCode != null) {
                    spec = spec.and(assetCategoryCode);
                }
                if (assetCode != null) {
                    spec = spec.and(assetName);
                    spec = spec.or(assetCode);
                }
                try {
                    assets = assetRepository.findAll(spec);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new DataNotFoundException(ErrorCode.ERR_RETRIEVE_ASSET_FAIL);
                }
                viewAssetsDTO = assetConverter.convertToListDTO(assets);
            }

            NumberOfAssetDTO numberOfAssetDto = new NumberOfAssetDTO();

            numberOfAssetDto.setNumberOfEntity((long) viewAssetsDTO.size());

            responseDto.setData(numberOfAssetDto);
            responseDto.setSuccessCode(SuccessCode.ASSET_COUNT_SUCCESS);
            return responseDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataNotFoundException(ErrorCode.ERR_COUNT_ASSET_FAIL);
        }
    }

}
