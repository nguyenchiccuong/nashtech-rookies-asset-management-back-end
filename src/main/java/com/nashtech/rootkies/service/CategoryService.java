package com.nashtech.rootkies.service;


import com.nashtech.rootkies.model.Category;

import java.util.Optional;

public interface CategoryService {

    Category create(Category category);

    Optional<Category> findCategory(Long cateId);

    Category update(Category cate);

    Boolean delete(Long cateId);

}
