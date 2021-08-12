package com.nashtech.rootkies.repository;

import java.util.List;
import java.util.Optional;

import com.nashtech.rootkies.model.Asset;
import com.nashtech.rootkies.model.Assignment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long>, JpaSpecificationExecutor<Assignment> {

    List<Assignment> findByAsset(Asset asset);

    @Query("SELECT COUNT (*) FROM Assignment a WHERE a.assignedBy.location.locationId = ?1 AND a.isDeleted = false AND (a.state = 1 OR a.state = 2 OR a.state = 3)")
    Long CountAllByLocationAndDefaultState(Long locationId);

    @Query("FROM Assignment a WHERE a.assignedBy.location.locationId = ?1 AND a.isDeleted = false AND (a.state = 1 OR a.state = 2 OR a.state = 3)")
    Page<Assignment> getAllByLocationAndDefaultState(Pageable page, Long locationId);

    @Query("FROM Assignment a WHERE a.assignedBy.location.locationId = ?1 AND a.isDeleted = false AND a.assignmentId = ?2")
    Optional<Assignment> findByAssignmentId(Long locationId, Long assignmentId);
}
