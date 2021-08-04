package com.nashtech.rootkies.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class Location {
    @Id
    @GeneratedValue
    @Column(name = "locationid")
    private Long locationId;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "location")
    private Collection<User> users;

    @OneToMany(mappedBy = "location")
    private Collection<Asset> assets;
}
