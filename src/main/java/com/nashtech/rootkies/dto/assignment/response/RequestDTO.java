package com.nashtech.rootkies.dto.assignment.response;

import java.time.LocalDateTime;

public class RequestDTO {
    private Long requestId;

    private UserDTO requestedBy;

    private UserDTO acceptedBy;

    private LocalDateTime returnedDate;

    private Short state;

    private Boolean isDeleted;
}
