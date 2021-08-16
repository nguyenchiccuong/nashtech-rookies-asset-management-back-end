package com.nashtech.rootkies.repository;

import java.util.Optional;

import com.nashtech.rootkies.model.Request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>, JpaSpecificationExecutor<Request> {
    @Query("FROM Request r WHERE r.requestedBy.location.locationId = ?1 AND r.isDeleted = false AND r.requestId = ?2")
    Optional<Request> findByRequestId(Long locationId, Long requestId);

}
