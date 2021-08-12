package com.nashtech.rootkies.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import com.nashtech.rootkies.model.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset, String>, JpaSpecificationExecutor<Asset> {

    @Query("FROM Asset a WHERE a.location.locationId = ?1 AND a.isDeleted = false and (a.state = 1 or a.state = 2 or a.state = 3)")
    public Page<Asset> getAllByLocationAndDefaultState(Pageable page, Long locationId);

    @Query("SELECT COUNT (*) FROM Asset a WHERE a.location.locationId = ?1 AND a.isDeleted = false and (a.state = 1 or a.state = 2 or a.state = 3)")
    public Long CountAllByLocationAndDefaultState(Long locationId);

    @Query("FROM Asset a WHERE a.location.locationId = ?1 AND a.isDeleted = false AND a.assetCode = ?2")
    Optional<Asset> findByAssetCode(Long locationId, String assetCode);

    public List<Asset> findByAssetCodeStartingWithOrderByAssetCodeDesc(String categoryCode);
}
