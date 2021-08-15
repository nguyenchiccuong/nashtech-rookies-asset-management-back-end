package com.nashtech.rootkies.service.impl;

import java.util.List;
import java.util.Optional;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.constants.State;
import com.nashtech.rootkies.constants.SuccessCode;
import com.nashtech.rootkies.converter.AssignmentConverter;
import com.nashtech.rootkies.dto.assignment.request.SearchFilterSortAssignmentDTO;
import com.nashtech.rootkies.dto.assignment.response.NumberOfAssignmentDTO;
import com.nashtech.rootkies.dto.assignment.response.ViewAssignmentDTO;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.enums.SortType;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.exception.DeleteDataFailException;
import com.nashtech.rootkies.exception.UpdateDataFailException;
import com.nashtech.rootkies.model.Asset;
import com.nashtech.rootkies.model.Assignment;
import com.nashtech.rootkies.repository.AssetRepository;
import com.nashtech.rootkies.repository.AssignmentRepository;
import com.nashtech.rootkies.repository.specs.AssignmentSpecification;
import com.nashtech.rootkies.repository.specs.SearchCriteria;
import com.nashtech.rootkies.repository.specs.SearchOperation;
import com.nashtech.rootkies.service.AssignmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;

    private final AssignmentConverter assignmentConverter;

    private final AssetRepository assetRepository;

    @Autowired
    public AssignmentServiceImpl(AssignmentRepository assignmentRepository, AssignmentConverter assignmentConverter,
            AssetRepository assetRepository) {
        this.assignmentRepository = assignmentRepository;
        this.assignmentConverter = assignmentConverter;
        this.assetRepository = assetRepository;
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
                throw new DataNotFoundException(ErrorCode.ERR_ASSIGNMENT_ID_NOT_FOUND);
            }

            ViewAssignmentDTO viewAssignmentDTO = assignmentConverter.convertToViewDTO(assignment.get());

            responseDto.setData(viewAssignmentDTO);
            responseDto.setSuccessCode(SuccessCode.ASSIGNMENT_LOADED_SUCCESS);
            return responseDto;
        } catch (Exception e) {
            throw new DataNotFoundException(ErrorCode.ERR_ASSIGNMENT_ID_NOT_FOUND);
        }
    }

    private Pageable getPaging(Integer pageNum, Integer numOfItems,
            SearchFilterSortAssignmentDTO searchFilterSortAssignmentDTO) {
        if (!(searchFilterSortAssignmentDTO.getSortField().isBlank()
                || searchFilterSortAssignmentDTO.getSortType().isBlank())) {

            if (searchFilterSortAssignmentDTO.getSortField().equalsIgnoreCase("no")) {
                if (searchFilterSortAssignmentDTO.getSortType().equalsIgnoreCase(SortType.DSC.toString())) {
                    return PageRequest.of(pageNum, numOfItems, Sort.by("assignmentId").descending());
                } else {
                    return PageRequest.of(pageNum, numOfItems, Sort.by("assignmentId").ascending());
                }
            } else if (searchFilterSortAssignmentDTO.getSortField().equalsIgnoreCase("assetCode")) {
                if (searchFilterSortAssignmentDTO.getSortType().equalsIgnoreCase(SortType.DSC.toString())) {
                    return PageRequest.of(pageNum, numOfItems, Sort.by("asset.assetCode").descending());
                } else {
                    return PageRequest.of(pageNum, numOfItems, Sort.by("asset.assetCode").ascending());
                }
            } else if (searchFilterSortAssignmentDTO.getSortField().equalsIgnoreCase("assetName")) {
                if (searchFilterSortAssignmentDTO.getSortType().equalsIgnoreCase(SortType.DSC.toString())) {
                    return PageRequest.of(pageNum, numOfItems, Sort.by("asset.assetName").descending());
                } else {
                    return PageRequest.of(pageNum, numOfItems, Sort.by("asset.assetName").ascending());
                }
            } else if (searchFilterSortAssignmentDTO.getSortField().equalsIgnoreCase("assignTo")) {
                if (searchFilterSortAssignmentDTO.getSortType().equalsIgnoreCase(SortType.DSC.toString())) {
                    return PageRequest.of(pageNum, numOfItems, Sort.by("assignTo.username").descending());
                } else {
                    return PageRequest.of(pageNum, numOfItems, Sort.by("assignTo.username").ascending());
                }
            } else if (searchFilterSortAssignmentDTO.getSortField().equalsIgnoreCase("assignBy")) {
                if (searchFilterSortAssignmentDTO.getSortType().equalsIgnoreCase(SortType.DSC.toString())) {
                    return PageRequest.of(pageNum, numOfItems, Sort.by("assignBy.username").descending());
                } else {
                    return PageRequest.of(pageNum, numOfItems, Sort.by("assignBy.username").ascending());
                }
            } else if (searchFilterSortAssignmentDTO.getSortField().equalsIgnoreCase("category")) {
                if (searchFilterSortAssignmentDTO.getSortType().equalsIgnoreCase(SortType.DSC.toString())) {
                    return PageRequest.of(pageNum, numOfItems, Sort.by("asset.category.categoryName").descending());
                } else {
                    return PageRequest.of(pageNum, numOfItems, Sort.by("asset.category.categoryName").ascending());
                }
            } else {
                if (searchFilterSortAssignmentDTO.getSortType().equalsIgnoreCase(SortType.DSC.toString())) {
                    return PageRequest.of(pageNum, numOfItems,
                            Sort.by(searchFilterSortAssignmentDTO.getSortField()).descending());
                } else {
                    return PageRequest.of(pageNum, numOfItems,
                            Sort.by(searchFilterSortAssignmentDTO.getSortField()).ascending());
                }
            }
        } else {
            return PageRequest.of(pageNum, numOfItems, Sort.by("assignedDate").descending());
        }
    }

    @Override
    public ResponseDTO retrieveAssignmentHavingFilterSearchSort(Integer pageNum, Integer numOfItems,
            SearchFilterSortAssignmentDTO searchFilterSortAssignmentDTO, Long locationId) throws DataNotFoundException {
        try {
            ResponseDTO responseDto = new ResponseDTO();
            Pageable page = this.getPaging(pageNum, numOfItems, searchFilterSortAssignmentDTO);

            // search assignment (by asset code or asset name or assignee’s username) and
            // filter assignment (by state, assigned date)

            AssignmentSpecification assetCode = null;
            AssignmentSpecification assetName = null;
            AssignmentSpecification assignedTo = null;
            AssignmentSpecification state = null;
            AssignmentSpecification date = null;
            AssignmentSpecification assignmentLocation = new AssignmentSpecification();
            assignmentLocation.add(new SearchCriteria("assignedBy", locationId, SearchOperation.EQUAL));
            AssignmentSpecification assignmentIsDeleted = new AssignmentSpecification();
            assignmentIsDeleted.add(new SearchCriteria("isDeleted", false, SearchOperation.EQUAL));

            if (!searchFilterSortAssignmentDTO.getStates().isEmpty()) {
                state = new AssignmentSpecification();
                state.add(new SearchCriteria("state", searchFilterSortAssignmentDTO.getStates(), SearchOperation.IN));
            }
            if (searchFilterSortAssignmentDTO.getLocalDateTime() != null) {
                date = new AssignmentSpecification();
                date.add(new SearchCriteria("assignedDate", searchFilterSortAssignmentDTO.getLocalDateTime(),
                        SearchOperation.EQUAL));
            }
            if (!searchFilterSortAssignmentDTO.getSearchKeyWord().isBlank()) {
                assetCode = new AssignmentSpecification();
                assetCode.add(new SearchCriteria("assetCode", searchFilterSortAssignmentDTO.getSearchKeyWord(),
                        SearchOperation.MATCH));

                assetName = new AssignmentSpecification();
                assetName.add(new SearchCriteria("assetName", searchFilterSortAssignmentDTO.getSearchKeyWord(),
                        SearchOperation.MATCH));

                assignedTo = new AssignmentSpecification();
                assignedTo.add(new SearchCriteria("assignedTo", searchFilterSortAssignmentDTO.getSearchKeyWord(),
                        SearchOperation.MATCH));
            }

            Specification spec = Specification.where(assignmentLocation).and(assignmentIsDeleted);

            if (state != null) {
                spec = spec.and(state);
            }
            if (date != null) {
                spec = spec.and(date);
            }
            if (assetCode != null) {
                spec = spec.and(Specification.where(assetCode).or(assetName).or(assignedTo));
            }

            Page<Assignment> assignments;

            try {
                assignments = assignmentRepository.findAll(spec, page);
            } catch (Exception e) {
                e.printStackTrace();
                throw new DataNotFoundException(ErrorCode.ERR_RETRIEVE_ASSIGNMENT_FAIL);
            }
            List<ViewAssignmentDTO> viewAssignmentDTO = assignmentConverter.convertToListDTO(assignments);

            responseDto.setData(viewAssignmentDTO);
            responseDto.setSuccessCode(SuccessCode.ASSIGNMENT_LOADED_SUCCESS);
            return responseDto;

        } catch (Exception e) {
            e.printStackTrace();
            throw new DataNotFoundException(ErrorCode.ERR_RETRIEVE_ASSIGNMENT_FAIL);
        }
    }

    @Override
    public ResponseDTO countAssignmentHavingFilterSearchSort(
            SearchFilterSortAssignmentDTO searchFilterSortAssignmentDTO, Long locationId) throws DataNotFoundException {
        try {
            ResponseDTO responseDto = new ResponseDTO();
            // search assignment (by asset code or asset name or assignee’s username) and
            // filter assignment (by state, assigned date)

            AssignmentSpecification assetCode = null;
            AssignmentSpecification assetName = null;
            AssignmentSpecification assignedTo = null;
            AssignmentSpecification state = null;
            AssignmentSpecification date = null;
            AssignmentSpecification assignmentLocation = new AssignmentSpecification();
            assignmentLocation.add(new SearchCriteria("assignedBy", locationId, SearchOperation.EQUAL));
            AssignmentSpecification assignmentIsDeleted = new AssignmentSpecification();
            assignmentIsDeleted.add(new SearchCriteria("isDeleted", false, SearchOperation.EQUAL));

            if (!searchFilterSortAssignmentDTO.getStates().isEmpty()) {
                state = new AssignmentSpecification();
                state.add(new SearchCriteria("state", searchFilterSortAssignmentDTO.getStates(), SearchOperation.IN));
            }
            if (searchFilterSortAssignmentDTO.getLocalDateTime() != null) {
                date = new AssignmentSpecification();
                date.add(new SearchCriteria("assignedDate", searchFilterSortAssignmentDTO.getLocalDateTime(),
                        SearchOperation.EQUAL));
            }
            if (!searchFilterSortAssignmentDTO.getSearchKeyWord().isBlank()) {
                assetCode = new AssignmentSpecification();
                assetCode.add(new SearchCriteria("assetCode", searchFilterSortAssignmentDTO.getSearchKeyWord(),
                        SearchOperation.MATCH));

                assetName = new AssignmentSpecification();
                assetName.add(new SearchCriteria("assetName", searchFilterSortAssignmentDTO.getSearchKeyWord(),
                        SearchOperation.MATCH));

                assignedTo = new AssignmentSpecification();
                assignedTo.add(new SearchCriteria("assignedTo", searchFilterSortAssignmentDTO.getSearchKeyWord(),
                        SearchOperation.MATCH));
            }

            Specification spec = Specification.where(assignmentLocation).and(assignmentIsDeleted);

            if (state != null) {
                spec = spec.and(state);
            }
            if (date != null) {
                spec = spec.and(date);
            }
            if (assetCode != null) {
                spec = spec.and(Specification.where(assetCode).or(assetName).or(assignedTo));
            }

            List<Assignment> assignments;

            try {
                assignments = assignmentRepository.findAll(spec);
            } catch (Exception e) {
                e.printStackTrace();
                throw new DataNotFoundException(ErrorCode.ERR_COUNT_ASSIGNMENT_FAIL);
            }
            List<ViewAssignmentDTO> viewAssignmentDTO = assignmentConverter.convertToListDTO(assignments);
            NumberOfAssignmentDTO numberOfAssignmentDTO = new NumberOfAssignmentDTO();

            numberOfAssignmentDTO.setNumberOfEntity((long) viewAssignmentDTO.size());

            responseDto.setData(numberOfAssignmentDTO);
            responseDto.setSuccessCode(SuccessCode.ASSIGNMENT_COUNT_SUCCESS);
            return responseDto;

        } catch (Exception e) {
            e.printStackTrace();
            throw new DataNotFoundException(ErrorCode.ERR_COUNT_ASSIGNMENT_FAIL);
        }
    }

    @Override
    public ResponseDTO deleteAssetByAssignmentId(Long locationId, Long assignmentId)
            throws DataNotFoundException, DeleteDataFailException {

        ResponseDTO responseDto = new ResponseDTO();
        Optional<Assignment> assignment;

        assignment = assignmentRepository.findByAssignmentId(locationId, assignmentId);
        if (!assignment.isPresent()) {
            throw new DataNotFoundException(ErrorCode.ERR_ASSIGNMENT_ID_NOT_FOUND);
        }
        Assignment assignmentSave = assignment.get();
        if (assignmentSave.getState() != State.WAITING_FOR_ACCEPTANCE) {
            throw new DeleteDataFailException(ErrorCode.ERR_ASSIGNMENT_DELETE_FAIL_DUE_TO_STATE);
        }
        try {
            assignmentSave.setIsDeleted(true);
            assignmentSave.getAsset().setState(State.AVAILABLE);
            assignmentRepository.save(assignmentSave);

            responseDto.setSuccessCode(SuccessCode.ASSIGNMENT_DELETE_SUCCESS);
            return responseDto;
        } catch (Exception e) {
            throw new DeleteDataFailException(ErrorCode.ERR_ASSIGNMENT_DELETE_FAIL);
        }
    }

    @Override
    public ResponseDTO editAssignment(Assignment assignment, String assetCode) throws UpdateDataFailException {
        ResponseDTO responseDto = new ResponseDTO();
        try {
            Asset assetUp = assetRepository.findById(assetCode)
                    .orElseThrow(() -> new DataNotFoundException(ErrorCode.ASSET_NOT_FOUND));

            if (!assignment.getAsset().getAssetCode().equalsIgnoreCase(assetCode)) {
                Asset assetCur = assignment.getAsset();
                assetCur.setState(State.AVAILABLE);
                assetRepository.save(assetCur);

                assetUp.setState(State.ASSIGNED);
                //assetRepository.save(assetUp);
                assignment.setAsset(assetUp);
            }

            ViewAssignmentDTO viewAssignmentDTO = assignmentConverter
                    .convertToViewDTO(assignmentRepository.save(assignment));

            responseDto.setData(viewAssignmentDTO);
            responseDto.setSuccessCode(SuccessCode.ASSIGNMENT_UPDATE_SUCCESS);
            return responseDto;
        } catch (Exception e) {
            throw new UpdateDataFailException(ErrorCode.ERR_ASSIGNMENT_UPDATE_FAIL);
        }
    }

}
