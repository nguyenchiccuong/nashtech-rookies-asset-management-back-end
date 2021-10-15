package com.nashtech.rootkies.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "assignments",
        indexes = {
                @Index(name = "assignment_assignedto_idx" , columnList = "assignedto"),
                @Index(name = "assignment_assetcode_idx", columnList = "assetcode"),
                @Index(name = "assignment_assigneddate_idx", columnList = "assigneddate"),
                @Index(name = "assignment_state_idx", columnList = "state")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assignment {
    @Id
    @GeneratedValue
    @Column(name = "assignmentid")
    private Long assignmentId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "assignedto", referencedColumnName = "staffcode")
    private User assignedTo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "assignedby", referencedColumnName = "staffcode")
    private User assignedBy;

    @NotNull
    @Column(name = "assigneddate")
    private LocalDateTime assignedDate;

    @NotNull
    @Column(name = "state")
    private Short state;

    @Column(name = "note")
    private String note;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "assetcode")
    private Asset asset;

    @NotNull
    @Column(name = "isdeleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "assignment")
    private Collection<Request> requests;
}
