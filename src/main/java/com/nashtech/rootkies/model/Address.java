package com.nashtech.rootkies.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "country" , nullable = false )
    private String country;
    @Column(name = "state")
    private String state;
    @Column(name = "city" , nullable = false )
    private String city;
    @Column(name = "district" , nullable = false )
    private String district;
    @Column(name = "ward"  , nullable = false )
    private String ward;
    @Column(name = "street_address"  , nullable = false )
    private String streetAddress;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    @Column(name = "is_deleted")
    private boolean isDeleted;

}
