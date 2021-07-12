package com.nashtech.rootkies.converter;

import com.nashtech.rootkies.dto.product.request.CreateProductDTO;
import com.nashtech.rootkies.model.Category;
import com.nashtech.rootkies.model.Product;
import com.nashtech.rootkies.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepository categoryRepository;


}
