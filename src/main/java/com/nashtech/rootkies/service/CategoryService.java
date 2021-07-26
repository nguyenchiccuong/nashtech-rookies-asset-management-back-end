package com.nashtech.rootkies.service;


import com.nashtech.rootkies.dto.category.request.SearchCategoryDTO;
import com.nashtech.rootkies.dto.category.response.CategoryDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.exception.DeleteDataFailException;
import com.nashtech.rootkies.exception.UpdateDataFailException;
import com.nashtech.rootkies.model.Category;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CategoryService {

    Boolean create(Category category) throws UpdateDataFailException;

    Optional<Category> findCategory(Long cateId) throws DataNotFoundException;

    Category update(Category cate) throws UpdateDataFailException;

    Boolean delete(Long cateId) throws DeleteDataFailException;

    boolean checkExist(String name);

    boolean checkExistById(Long categoryId);
    Page<CategoryDTO> search(SearchCategoryDTO searchRequest);
}
