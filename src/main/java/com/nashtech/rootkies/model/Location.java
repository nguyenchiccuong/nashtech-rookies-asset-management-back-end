package com.nashtech.rootkies.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "locations",uniqueConstraints = {
            @UniqueConstraint(columnNames = "address")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {
    @Id
    @GeneratedValue
    @Column(name = "locationid")
    private Long locationId;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "location" , fetch = FetchType.LAZY)
    private Collection<User> users;

    @OneToMany(mappedBy = "location" , fetch = FetchType.LAZY)
    private Collection<Asset> assets;
}
