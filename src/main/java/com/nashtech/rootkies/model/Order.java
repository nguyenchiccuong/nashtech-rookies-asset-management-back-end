package com.nashtech.rootkies.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY )
    private User user;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "upadted_date")
    private LocalDateTime updatedDate;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address deliveryPlace;

    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Set<OrderedItem> orderedItems = new HashSet<>();

    public Order(){
        this.createdDate= LocalDateTime.now();
    }
}
