package com.nashtech.rootkies.controllers;

import javax.validation.Valid;

import com.nashtech.rootkies.dto.asset.reponse.DetailAssetDTO;
import com.nashtech.rootkies.dto.asset.reponse.NumberOfAssetDTO;
import com.nashtech.rootkies.dto.asset.request.SearchFilterSortAssetDTO;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.model.Asset;
import com.nashtech.rootkies.service.AssetService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/asset")
public class AssetController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssetController.class);

    private final AssetService assetService;

    @Autowired
    public AssetController(AssetService assetService) {
        this.assetService = assetService;

    }

    @GetMapping
    public ResponseEntity<ResponseDTO> retrieveAssets(@RequestParam(name = "page", required = true) Integer pageNum,
            @RequestParam(name = "size", required = true) Integer numOfItems) throws DataNotFoundException {
        // visualize the admin using in in sai gon
        Long locationId = (long) 101; // 101 mean sai gon
        return ResponseEntity.ok(assetService
                .retrieveAsset(PageRequest.of(pageNum, numOfItems, Sort.by("assetName").ascending()), locationId));
    }

    @GetMapping("/count")
    public ResponseEntity<ResponseDTO> countAsset() throws DataNotFoundException {
        // visualize the admin using in in sai gon
        Long locationId = (long) 101; // 101 mean sai gon
        return ResponseEntity.ok(assetService.countAsset(locationId));
    }

    @GetMapping("/{assetCode}")
    public ResponseEntity<ResponseDTO> retrieveAssetById(@PathVariable("assetCode") String assetCode)
            throws DataNotFoundException {
        // visualize the admin using in in sai gon
        Long locationId = (long) 101; // 101 mean sai gon
        return ResponseEntity.ok(assetService.retrieveAssetByAssetCode(locationId, assetCode));

    }

    @GetMapping("/filter-search-sort")
    public ResponseEntity<ResponseDTO> retrieveAssetHavingFilterSearchSort(
            @RequestParam(name = "page", required = true) Integer pageNum,
            @RequestParam(name = "size", required = true) Integer numOfItems,
            @RequestBody SearchFilterSortAssetDTO searchFilterSortAssetDTO) throws DataNotFoundException {
        // visualize the admin using in in sai gon
        Long locationId = (long) 101; // 101 mean sai gon
        return ResponseEntity.ok(assetService.retrieveAssetHavingFilterSearchSort(pageNum, numOfItems,
                searchFilterSortAssetDTO, locationId));
    }

    @GetMapping("/count/filter-search-sort")
    public ResponseEntity<ResponseDTO> countAssetHavingFilterSearchSort(
            @RequestBody SearchFilterSortAssetDTO searchFilterSortAssetDTO) throws DataNotFoundException {
        // visualize the admin using in in sai gon
        Long locationId = (long) 101; // 101 mean sai gon
        return ResponseEntity.ok(assetService.countAssetHavingFilterSearchSort(searchFilterSortAssetDTO, locationId));
    }

    // remeber to research valid only work when input or output

}
