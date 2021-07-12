package com.nashtech.rootkies.service.impl;

import com.nashtech.rootkies.dto.product.request.SearchProductDTO;
import com.nashtech.rootkies.dto.product.response.ProductDTO;
import com.nashtech.rootkies.model.Product;
import com.nashtech.rootkies.repository.ProductRepository;
import com.nashtech.rootkies.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean delete(Long id) {
        productRepository.deleteById(id);
        return productRepository.findById(id).isPresent() ? false : true;
    }

    @Override
    public Page<ProductDTO> search(SearchProductDTO searchDto) {
        List<Product> list = null;

        Page<ProductDTO> pages = null;

        Pageable pageRequest = PageRequest.of(searchDto.getSize() , searchDto.getPage() , Sort.by(searchDto.getSortBy()));
        list = productRepository.findAll(pageRequest).getContent();
        pages = new PageImpl(list);

        return pages;
    }
}
