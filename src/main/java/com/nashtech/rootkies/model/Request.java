package com.nashtech.rootkies.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
public class Request {
    @Id
    @GeneratedValue
    @Column(name = "requestid")
    private Long requestId;

    @ManyToOne
    @JoinColumn(name = "requestedby", referencedColumnName = "username", nullable = false)
    private User requestedBy;

    @ManyToOne
    @JoinColumn(name = "acceptedby", referencedColumnName = "username")
    private User acceptedBy;

    @Column(name = "returneddate")
    private LocalDateTime returnedDate;

    @Column(name = "state")
    private String state;

    @ManyToOne
    @JoinColumn(name = "assignmentid", nullable = false)
    private Assignment assignment;

    @Column(name = "isdeleted")
    private Boolean isDeleted;
}
