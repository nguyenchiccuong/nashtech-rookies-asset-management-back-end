package com.nashtech.rootkies.dto.assignment.response;

import java.time.LocalDateTime;

public class ViewAssignment {
    private Long assignmentId;

    private UserDTO assignedTo;

    private UserDTO assignedBy;

    private LocalDateTime assignedDate;

    private Short state;

    private String note;

    private AssetDTO asset;
}
