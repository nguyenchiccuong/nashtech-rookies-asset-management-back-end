package com.nashtech.rootkies.converter;

import com.nashtech.rootkies.dto.category.response.BasicCategoryDTO;
import com.nashtech.rootkies.model.Category;
import com.nashtech.rootkies.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryConverter {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepository categoryRepository;

    public BasicCategoryDTO convertEntityToBasicDTO(Category cate) {
        BasicCategoryDTO dto = modelMapper.map(cate, BasicCategoryDTO.class);
        return dto;
    }

    public List<BasicCategoryDTO> convertToListBasicDTO(List<Category> cates) {
        return cates.stream().map(c -> convertEntityToBasicDTO(c)).collect(Collectors.toList());
    }
}
