package com.nashtech.rootkies.dto.assignment.response;

import java.time.LocalDateTime;
import java.util.Collection;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ViewAssignmentDTO {
    private Long assignmentId;

    private UserDTO assignedTo;

    private UserDTO assignedBy;

    private LocalDateTime assignedDate;

    private Short state;

    private String note;

    private AssetDTO asset;

    private Collection<RequestDTO> requests;
}
