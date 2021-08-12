package com.nashtech.rootkies.service.impl;

import java.util.List;
import java.util.Optional;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.constants.SuccessCode;
import com.nashtech.rootkies.converter.AssignmentConverter;
import com.nashtech.rootkies.dto.assignment.request.SearchFilterSortAssignmentDTO;
import com.nashtech.rootkies.dto.assignment.response.NumberOfAssignmentDTO;
import com.nashtech.rootkies.dto.assignment.response.ViewAssignmentDTO;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.model.Assignment;
import com.nashtech.rootkies.repository.AssignmentRepository;
import com.nashtech.rootkies.service.AssignmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;

    private final AssignmentConverter assignmentConverter;

    @Autowired
    public AssignmentServiceImpl(AssignmentRepository assignmentRepository, AssignmentConverter assignmentConverter) {
        this.assignmentRepository = assignmentRepository;
        this.assignmentConverter = assignmentConverter;
    }

    @Override
    public ResponseDTO countAssignment(Long locationId) throws DataNotFoundException {
        try {
            ResponseDTO responseDto = new ResponseDTO();
            NumberOfAssignmentDTO numberOfAssignmentDTO = new NumberOfAssignmentDTO();
            try {

                numberOfAssignmentDTO
                        .setNumberOfEntity(assignmentRepository.CountAllByLocationAndDefaultState(locationId));
            } catch (Exception e) {
                throw new DataNotFoundException(ErrorCode.ERR_COUNT_ASSIGNMENT_FAIL);
            }

            responseDto.setData(numberOfAssignmentDTO);
            responseDto.setSuccessCode(SuccessCode.ASSIGNMENT_COUNT_SUCCESS);
            return responseDto;
        } catch (Exception e) {
            throw new DataNotFoundException(ErrorCode.ERR_COUNT_ASSIGNMENT_FAIL);
        }
    }

    @Override
    public ResponseDTO retrieveAssignments(Pageable page, Long locationId) throws DataNotFoundException {
        try {
            ResponseDTO responseDto = new ResponseDTO();
            Page<Assignment> assignments;
            try {
                assignments = assignmentRepository.getAllByLocationAndDefaultState(page, locationId);
            } catch (Exception e) {
                throw new DataNotFoundException(ErrorCode.ERR_RETRIEVE_ASSIGNMENT_FAIL);
            }
            List<ViewAssignmentDTO> viewAssignmentDTO = assignmentConverter.convertToListDTO(assignments);

            responseDto.setData(viewAssignmentDTO);
            responseDto.setSuccessCode(SuccessCode.ASSIGNMENT_LOADED_SUCCESS);
            return responseDto;
        } catch (Exception e) {
            throw new DataNotFoundException(ErrorCode.ERR_RETRIEVE_ASSIGNMENT_FAIL);
        }
    }

    @Override
    public ResponseDTO retrieveAssignmentByAssignmentId(Long locationId, Long assignmentId)
            throws DataNotFoundException {
        try {
            ResponseDTO responseDto = new ResponseDTO();
            Optional<Assignment> assignment;

            assignment = assignmentRepository.findByAssignmentId(locationId, assignmentId);
            if (!assignment.isPresent()) {
                throw new DataNotFoundException(ErrorCode.ERR_ASSETCODE_NOT_FOUND);
            }

            ViewAssignmentDTO viewAssignmentDTO = assignmentConverter.convertToViewDTO(assignment.get());

            responseDto.setData(viewAssignmentDTO);
            responseDto.setSuccessCode(SuccessCode.ASSIGNMENT_LOADED_SUCCESS);
            return responseDto;
        } catch (Exception e) {
            throw new DataNotFoundException(ErrorCode.ERR_ASSIGNMENT_ID_NOT_FOUND);
        }
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
