package com.nashtech.rootkies.model;

import lombok.*;

import javax.persistence.*;
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
@Builder
public class Asset {
        @Id
        @Column(name = "assetcode")
        private String assetCode;

        @Column(name = "assetname")
        private String assetName;

        @Column(name = "state")
        private Short state;

        @Column(name = "installdate")
        private LocalDateTime installDate;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "locationid" )
        private Location location;

        @Column(name = "specification")
        private String specification;

        @Column(name = "isdeleted")
        private Boolean isDeleted;

        @ManyToOne
        @JoinColumn(name = "categorycode")
        private Category category;

        @OneToMany(mappedBy = "asset" ,fetch = FetchType.LAZY)
        private Collection<Assignment> assignments;
}
