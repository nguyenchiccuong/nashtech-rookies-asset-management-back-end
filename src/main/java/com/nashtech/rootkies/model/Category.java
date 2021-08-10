package com.nashtech.rootkies.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "categories",
       uniqueConstraints = {
            @UniqueConstraint(columnNames = "categorycode"),
            @UniqueConstraint(columnNames = "categoryname")
       },
       indexes = {
            @Index(name = "category_name_idx" , columnList = "categoryname")
        }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @Column(name = "categorycode")
    private String categoryCode;

    @Column(name = "categoryname")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private Collection<Asset> assets;
}
