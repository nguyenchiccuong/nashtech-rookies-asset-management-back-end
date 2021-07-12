package com.nashtech.rootkies.repository;

import com.nashtech.rootkies.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface OrganizationRepository extends JpaRepository<Organization , Long> {
    Optional<Organization> findByName(String apple);
}
