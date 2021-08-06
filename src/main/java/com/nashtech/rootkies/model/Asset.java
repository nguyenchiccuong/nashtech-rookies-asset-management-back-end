package com.nashtech.rootkies.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "assets", indexes = { @Index(name = "asset_name_idx", columnList = "assetname"),
                @Index(name = "asset_category_idx", columnList = "categorycode"),
                @Index(name = "asset_state_idx", columnList = "state") })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asset {
        @Id
        @Column(name = "assetcode")
        private String assetCode;

        @NotBlank
        @Column(name = "assetname")
        private String assetName;

        @NotNull
        @Column(name = "state")
        private Short state;

        @NotNull
        @Column(name = "installdate")
        private LocalDateTime installDate;

        @NotNull
        @ManyToOne
        @JoinColumn(name = "locationid")
        private Location location;

        @NotBlank
        @Column(name = "specification")
        private String specification;

        @NotNull
        @Column(name = "isdeleted")
        private Boolean isDeleted;

        @NotNull
        @ManyToOne
        @JoinColumn(name = "categorycode")
        private Category category;

        @OneToMany(mappedBy = "asset")
        private Collection<Assignment> assignments;
}
