package com.nashtech.rootkies.repository;

import com.nashtech.rootkies.model.Asset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset, String>{
    
}
