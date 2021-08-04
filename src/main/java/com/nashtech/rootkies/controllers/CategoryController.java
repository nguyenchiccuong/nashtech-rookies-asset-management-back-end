package com.nashtech.rootkies.controllers;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.converter.CategoryConverter;
import com.nashtech.rootkies.dto.category.response.CategoryDTO;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.model.Category;
import com.nashtech.rootkies.service.CategoryService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@Api( tags = "Category")
public class CategoryController {

    /*@Autowired
    CategoryConverter categoryConverter;

    @Autowired
    CategoryService categoryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @ResponseBody
    @GetMapping("/{cateId}")
    public ResponseEntity<ResponseDTO> getCategory(@PathVariable("cateId") Long cateId) throws DataNotFoundException {
        ResponseDTO response = new ResponseDTO();
        Optional<Category> category = categoryService.findCategory(cateId);
        CategoryDTO cateDao = categoryConverter.convertEntityToDTO(category.get());
        response.setData(cateDao);
        return ResponseEntity.ok().body(response);
    }*/
}
