package com.nashtech.rootkies.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.*;

@Entity
@Table(name = "categories", uniqueConstraints = { @UniqueConstraint(columnNames = "categorycode"),
        @UniqueConstraint(columnNames = "categoryname") }, indexes = {
                @Index(name = "category_name_idx", columnList = "categoryname") })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @Column(name = "categorycode")
    private String categoryCode;

    @NotBlank
    @Column(name = "categoryname")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private Collection<Asset> assets;
}
