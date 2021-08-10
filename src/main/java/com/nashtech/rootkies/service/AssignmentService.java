package com.nashtech.rootkies.service;

import com.nashtech.rootkies.dto.assignment.request.SearchFilterSortAssignmentDTO;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;

import org.springframework.data.domain.Pageable;

public interface AssignmentService {
        // Assignment list can be sorted by column titles (default: ascending)

        // -------------------------------------------------------------------------------------
        // By default, it displays all assets having state = Available, Not available,
        // Assigned and location of the user
        public ResponseDTO countAssignment(Long locationId) throws DataNotFoundException;

        public ResponseDTO retrieveAssignments(Pageable page, Long locationId) throws DataNotFoundException;
        // -------------------------------------------------------------------------------------

        public ResponseDTO retrieveAssignmentByAssignmentCode(Long locationId, String assignmentCode)
                        throws DataNotFoundException;

        public ResponseDTO countAssignmentHavingFilterSearchSort();

        public ResponseDTO retrieveAssignmentHavingFilterSearchSort(Integer pageNum, Integer numOfItems,
                        SearchFilterSortAssignmentDTO searchFilterSortAssignmentDTO, Long locationId)
                        throws DataNotFoundException;

        public ResponseDTO countAssignmentHavingFilterSearchSort(
                        SearchFilterSortAssignmentDTO searchFilterSortAssignmentDTO, Long locationId)
                        throws DataNotFoundException;
}
