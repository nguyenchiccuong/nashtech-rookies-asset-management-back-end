package com.nashtech.rootkies.controllers;

import com.nashtech.rootkies.converter.CategoryConverter;
import com.nashtech.rootkies.dto.category.request.CreateCategoryDTO;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.exception.ConvertEntityDTOException;
import com.nashtech.rootkies.exception.CreateDataFailException;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.exception.DuplicateDataException;
import com.nashtech.rootkies.model.Category;
import com.nashtech.rootkies.service.CategoryService;
import com.nashtech.rootkies.repository.CategoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/category")
@Tag(name = "CATEGORY", description = "CATEGORY API")
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    private final CategoryConverter categoryConverter;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryConverter categoryConverter,
            CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryConverter = categoryConverter;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> retrieveCategories() throws DataNotFoundException {
        return ResponseEntity.ok(categoryService.retrieveCategories());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> saveCategory(@Valid @RequestBody CreateCategoryDTO createCategoryDTO)
            throws ConvertEntityDTOException, CreateDataFailException, DuplicateDataException {

        Category category = categoryConverter.convertCreateCategoryDTOToEntity(createCategoryDTO);
        return ResponseEntity.ok(categoryService.saveCategory(category));
    }
}
