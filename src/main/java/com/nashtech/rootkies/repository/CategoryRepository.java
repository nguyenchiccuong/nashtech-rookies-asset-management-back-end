package com.nashtech.rootkies.repository;

import com.nashtech.rootkies.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

    Optional<Category> findByCategoryName(String name);
    Boolean existsByCategoryName(String name);
    Page<Category> findAll(Specification<Category> categories, Pageable pageRequest);
}
