package com.nashtech.rootkies.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "locations", uniqueConstraints = { @UniqueConstraint(columnNames = "address") })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "location_sequence"),
            @Parameter(name = "increment_size", value = "1"), @Parameter(name = "initial_value", value = "100") })
    @Column(name = "locationid")
    private Long locationId;

    @NotBlank
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "location")
    private Collection<User> users;

    @OneToMany(mappedBy = "location")
    private Collection<Asset> assets;
}
