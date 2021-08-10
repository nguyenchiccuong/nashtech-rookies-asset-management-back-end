package com.nashtech.rootkies.service.impl;

import com.nashtech.rootkies.dto.assignment.request.SearchFilterSortAssignmentDTO;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.service.AssignmentService;

import org.springframework.data.domain.Pageable;

public class AssignmentServiceImpl implements AssignmentService {

    @Override
    public ResponseDTO countAssignment(Long locationId) throws DataNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseDTO retrieveAssignments(Pageable page, Long locationId) throws DataNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseDTO retrieveAssignmentByAssignmentCode(Long locationId, String assignmentCode)
            throws DataNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseDTO countAssignmentHavingFilterSearchSort() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseDTO retrieveAssignmentHavingFilterSearchSort(Integer pageNum, Integer numOfItems,
            SearchFilterSortAssignmentDTO searchFilterSortAssignmentDTO, Long locationId) throws DataNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseDTO countAssignmentHavingFilterSearchSort(
            SearchFilterSortAssignmentDTO searchFilterSortAssignmentDTO, Long locationId) throws DataNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

}
