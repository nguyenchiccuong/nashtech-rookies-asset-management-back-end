package com.nashtech.rootkies.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id" , unique = true , length = 36)
    private Long id;

    @Column(name = "name" , unique = true , nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    public Category parent;

    @ManyToMany
    @JoinTable(name = "subcategories",
            joinColumns = { @JoinColumn(name = "parent_id") },
            inverseJoinColumns = { @JoinColumn(name = "id")
    })
    private Set<Category> subCategories = new HashSet<>();

    @Column(name = "description")
    private String description;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    public Category(){
        this.createdDate = LocalDateTime.now();
    }

    public Category(String name , Long parentIt,String description){
        this.createdDate = LocalDateTime.now();
    }
}
