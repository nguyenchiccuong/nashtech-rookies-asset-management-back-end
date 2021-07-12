package com.nashtech.rootkies.service;


import com.nashtech.rootkies.dto.product.request.SearchProductDTO;
import com.nashtech.rootkies.dto.product.response.ProductDTO;
import com.nashtech.rootkies.model.Product;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ProductService {
    Product create(Product product);
    Optional<Product> findById(Long id);
    Product update(Product map);
    boolean delete(Long id);
    Page<ProductDTO> search(SearchProductDTO searchDto);
}
