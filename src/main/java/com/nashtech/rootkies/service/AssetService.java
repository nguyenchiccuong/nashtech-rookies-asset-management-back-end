package com.nashtech.rootkies.service;

import com.nashtech.rootkies.dto.asset.request.SearchFilterSortAssetDTO;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;

import org.springframework.data.domain.Pageable;

public interface AssetService {
    // Asset list can be sorted by column titles (default: ascending)

    // -------------------------------------------------------------------------------------
    // By default, it displays all assets having state = Available, Not available,
    // Assigned and location of the user
    public ResponseDTO countAsset(Long locationId) throws DataNotFoundException;

    public ResponseDTO retrieveAsset(Pageable page, Long locationId) throws DataNotFoundException;
    // -------------------------------------------------------------------------------------

    public ResponseDTO retrieveAssetByAssetCode(Long locationId, String assetCode) throws DataNotFoundException;

    public ResponseDTO countAssetHavingFilterSearchSort();

    public ResponseDTO retrieveAssetHavingFilterSearchSort(Integer pageNum, Integer numOfItems,
            SearchFilterSortAssetDTO searchFilterSortAssetDTO, Long locationId) throws DataNotFoundException;
}
