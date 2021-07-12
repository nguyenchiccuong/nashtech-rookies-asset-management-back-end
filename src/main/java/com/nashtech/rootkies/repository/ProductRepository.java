package com.nashtech.rootkies.repository;


import com.nashtech.rootkies.model.Product;
import com.nashtech.rootkies.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    public Page<Product> findAll(Specification<Product> spec, Pageable pageable);

}
