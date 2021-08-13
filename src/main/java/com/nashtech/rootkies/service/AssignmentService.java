package com.nashtech.rootkies.service;

import com.nashtech.rootkies.dto.assignment.request.SearchFilterSortAssignmentDTO;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;

import org.springframework.data.domain.Pageable;

public interface AssignmentService {
        // search assignment (by asset code or asset name or assignee’s username) and
        // filter assignment (by state, assigned date)

        // sort by asset code, asset name, category, assigne date, state, assign to,
        // assign by, id
        // -------------------------------------------------------------------------------------
        // view assignment list, state = 1,2,3 and isdelete =false and location of the
        // user
        public ResponseDTO countAssignment(Long locationId) throws DataNotFoundException;

        public ResponseDTO retrieveAssignments(Pageable page, Long locationId) throws DataNotFoundException;
        // -------------------------------------------------------------------------------------

        public ResponseDTO retrieveAssignmentByAssignmentId(Long locationId, Long assignmentId)
                        throws DataNotFoundException;

        public ResponseDTO retrieveAssignmentHavingFilterSearchSort(Integer pageNum, Integer numOfItems,
                        SearchFilterSortAssignmentDTO searchFilterSortAssignmentDTO, Long locationId)
                        throws DataNotFoundException;

        public ResponseDTO countAssignmentHavingFilterSearchSort(
                        SearchFilterSortAssignmentDTO searchFilterSortAssignmentDTO, Long locationId)
                        throws DataNotFoundException;
}
