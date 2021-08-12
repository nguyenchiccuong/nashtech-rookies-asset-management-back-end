package com.nashtech.rootkies.repository;

import java.util.List;

import com.nashtech.rootkies.model.Asset;
import com.nashtech.rootkies.model.Assignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    
    List<Assignment> findByAsset(Asset asset);

    @Query(value = "SELECT CASE WHEN count(assignmentid) > 0 THEN true ELSE false END checkExist "+
            "FROM assignments a WHERE assignedby = ?1 or assignedto =?1 " , nativeQuery = true)
    Boolean checkAnyValidAssignment(String staffCode);
}
