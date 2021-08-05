package com.nashtech.rootkies.repository;

import com.nashtech.rootkies.model.Asset;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<Asset, String> {

    @Query("FROM Asset a WHERE a.location.locationId = ?1 AND a.isDeleted = false and (a.state = 1 or a.state = 2 or a.state = 3)")
    public Page<Asset> getAllByLocationAndDefaultState(Pageable page, Long locationId);

    @Query("SELECT COUNT (*) FROM Asset a WHERE a.location.locationId = ?1 AND a.isDeleted = false and (a.state = 1 or a.state = 2 or a.state = 3)")
    public Long CountAllByLocationAndDefaultState(Long locationId);
}
