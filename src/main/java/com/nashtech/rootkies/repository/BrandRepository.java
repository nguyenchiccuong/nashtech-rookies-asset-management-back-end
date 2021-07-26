package com.nashtech.rootkies.repository;

import com.nashtech.rootkies.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByName(String apple);
    Boolean existsByName(String name);
}
