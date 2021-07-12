package com.nashtech.rootkies.service.impl;

import com.nashtech.rootkies.model.Category;
import com.nashtech.rootkies.repository.CategoryRepository;
import com.nashtech.rootkies.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category create(Category cate) {
        return categoryRepository.save(cate);
    }

    @Override
    public Optional<Category> findCategory(Long cateId) {
        return categoryRepository.findById(cateId);
    }

    @Override
    public Category update(Category cate) {
        cate.setUpdatedDate(LocalDateTime.now());
        return categoryRepository.save(cate);
    }

    @Override
    public Boolean delete(Long cateId) {
        categoryRepository.deleteById(cateId);
        return categoryRepository.findById(cateId).isPresent() ? false : true;
    }
}
