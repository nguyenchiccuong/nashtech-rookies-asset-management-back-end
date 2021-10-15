package com.nashtech.rootkies.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "requests",
        indexes = {
                @Index(name = "request_requestedby_idx" , columnList = "requestedby"),
                @Index(name = "request_returneddate_idx" , columnList = "returneddate"),
                @Index(name = "request_state_idx" , columnList = "state"),
                @Index(name = "request_assignmentid_idx" , columnList = "assignmentid")
        }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Request {
    @Id
    @GeneratedValue
    @Column(name = "requestid")
    private Long requestId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "requestedby", referencedColumnName = "staffcode")
    private User requestedBy;

    @ManyToOne
    @JoinColumn(name = "acceptedby", referencedColumnName = "staffcode")
    private User acceptedBy;

    @Column(name = "returneddate")
    private LocalDateTime returnedDate;

    @NotNull
    @Column(name = "state")
    private Short state;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "assignmentid")
    private Assignment assignment;

    @NotNull
    @Column(name = "isdeleted")
    private Boolean isDeleted;
}
