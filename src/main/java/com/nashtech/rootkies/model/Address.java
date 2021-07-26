package com.nashtech.rootkies.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "address",
        indexes ={
            @Index(name = "address_id_index" , columnList = "id")
        }
)
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "country" , nullable = false )
    private String country;
    @NotBlank
    @Column(name = "city" , nullable = false )
    private String city;
    @NotBlank
    @Column(name = "district" , nullable = false )
    private String district;
    @NotBlank
    @Column(name = "ward"  , nullable = false )
    private String ward;
    @NotBlank
    @Column(name = "street_address"  , nullable = false )
    private String streetAddress;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    @Column(name = "is_deleted")
    private boolean isDeleted;

    public Address(String street,String ward , String district , String city , String country){
        this.streetAddress = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
        this.country = country;
    }

}
