package com.nashtech.rootkies.converter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.constants.State;
import com.nashtech.rootkies.dto.assignment.request.EditAssignmentDTO;
import com.nashtech.rootkies.dto.assignment.response.RequestDTO;
import com.nashtech.rootkies.dto.assignment.response.ViewAssignmentDTO;
import com.nashtech.rootkies.exception.ConvertEntityDTOException;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.exception.InvalidRequestDataException;
import com.nashtech.rootkies.model.Assignment;
import com.nashtech.rootkies.repository.AssignmentRepository;
import com.nashtech.rootkies.repository.AssetRepository;
import com.nashtech.rootkies.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class AssignmentConverter {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    UserRepository userRepository;

    public List<ViewAssignmentDTO> convertToListDTO(Page<Assignment> assignments) throws ConvertEntityDTOException {
        try {
            List<ViewAssignmentDTO> listViewAssignmentDTO = assignments.stream()
                    .map(assignment -> modelMapper.map(assignment, ViewAssignmentDTO.class))
                    .collect(Collectors.toList());
            listViewAssignmentDTO.forEach(e -> {
                Collection<RequestDTO> filteredCollection = e.getRequests().stream().filter(e1 -> !e1.getIsDeleted())
                        .collect(Collectors.toList());
                e.setRequests(filteredCollection);
            });
            return listViewAssignmentDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewAssignmentDTO> convertToListDTO(List<Assignment> assignments) throws ConvertEntityDTOException {
        try {
            List<ViewAssignmentDTO> listViewAssignmentDTO = assignments.stream()
                    .map(assignment -> modelMapper.map(assignment, ViewAssignmentDTO.class))
                    .collect(Collectors.toList());
            listViewAssignmentDTO.forEach(e -> {
                Collection<RequestDTO> filteredCollection = e.getRequests().stream().filter(e1 -> !e1.getIsDeleted())
                        .collect(Collectors.toList());
                e.setRequests(filteredCollection);
            });
            return listViewAssignmentDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public ViewAssignmentDTO convertToViewDTO(Assignment assignment) throws ConvertEntityDTOException {
        try {
            ViewAssignmentDTO viewAssignmentDTO = modelMapper.map(assignment, ViewAssignmentDTO.class);
            Collection<RequestDTO> filteredCollection = viewAssignmentDTO.getRequests().stream()
                    .filter(e -> !e.getIsDeleted()).collect(Collectors.toList());
            viewAssignmentDTO.setRequests(filteredCollection);
            return viewAssignmentDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public Assignment convertEditAssignmentDTOToEntity(Long locationId, @Valid EditAssignmentDTO editAssignmentDTO,
            String username) throws DataNotFoundException, InvalidRequestDataException {
        Assignment assignment = assignmentRepository.findByAssignmentId(locationId, editAssignmentDTO.getAssignmentId())
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.ERR_ASSIGNMENT_ID_NOT_FOUND));

        if (assignment.getState() != State.WAITING_FOR_ACCEPTANCE) {
            throw new InvalidRequestDataException(ErrorCode.ERR_ASSIGNMENT_ALREADY_ACCEPTED_OR_DECLINED);
        }

        LocalDateTime ldtNow = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime ldtToSet = editAssignmentDTO.getAssignedDate().withHour(0).withMinute(0).withSecond(0)
                .withNano(0);
        if (ldtToSet.compareTo(ldtNow) < 0) {
            throw new InvalidRequestDataException(ErrorCode.ERR_ASSIGNED_DATE_UPDATE_IS_EARLIER_THAN_CURRENT);
        }
        assignment.setAssignedDate(ldtToSet);

        if (assignment.getAsset().getAssetCode().equalsIgnoreCase(editAssignmentDTO.getAssetCode())) {
            assignment.setAsset(assetRepository.findByAssetCode(locationId, editAssignmentDTO.getAssetCode())
                    .orElseThrow(() -> new DataNotFoundException(ErrorCode.ASSET_NOT_FOUND)));
        } else {
            assignment.setAsset(assetRepository.findByAssetCode(locationId, editAssignmentDTO.getAssetCode())
                    .orElseThrow(() -> new DataNotFoundException(ErrorCode.ASSET_NOT_FOUND)));

            if (assignment.getAsset().getState() != State.AVAILABLE) {
                throw new InvalidRequestDataException(ErrorCode.ERR_ASSET_NOT_AVAILABLE);
            }
        }

        assignment.setAssignedTo(userRepository.findByStaffCode(locationId, editAssignmentDTO.getAssignedToUserId())
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.ERR_USER_NOT_FOUND)));

        assignment.setAssignedBy(userRepository.findByUsername(locationId, username)
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.ERR_USER_NOT_FOUND)));

        assignment.setNote(editAssignmentDTO.getNote());

        return assignment;
    }

}
