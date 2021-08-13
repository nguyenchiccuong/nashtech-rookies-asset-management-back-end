package com.nashtech.rootkies.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.dto.assignment.request.CreateAssignmentDTO;
import com.nashtech.rootkies.dto.assignment.response.RequestDTO;
import com.nashtech.rootkies.dto.assignment.response.ViewAssignmentDTO;
import com.nashtech.rootkies.exception.ConvertEntityDTOException;
import com.nashtech.rootkies.model.Asset;
import com.nashtech.rootkies.model.Assignment;

import com.nashtech.rootkies.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class AssignmentConverter {

    @Autowired
    ModelMapper modelMapper;

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

    public Assignment createDTOToEntity(CreateAssignmentDTO dto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return Assignment.builder()
                .assignedBy(User.builder().staffCode(dto.getAssignedBy()).build())
                .assignedTo(User.builder().staffCode(dto.getAssignedTo()).build())
                .note(dto.getNote())
                .state((short)1)
                .asset(Asset.builder().assetCode(dto.getAssetCode()).build())
                .isDeleted(false)
                .assignedDate(LocalDateTime.parse(dto.getAssignedDate() , formatter))
                .build();
    }


}
