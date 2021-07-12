package com.nashtech.rootkies.controllers;

import com.nashtech.rootkies.constants.ErrorCode;
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

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryService categoryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    /**
     *
     * @param cateId
     * @return
     * @throws DataNotFoundException
     */
    @ResponseBody
    @GetMapping("/{cateId}")
    public ResponseEntity<ResponseDTO> getCategory(@PathVariable("cateId") Long cateId) throws DataNotFoundException {
        ResponseDTO response = new ResponseDTO();
        try{
            Optional<Category> category = categoryService.findCategory(cateId);
            CategoryDTO cateDao = modelMapper.map(category.get() , CategoryDTO.class);
            response.setData(cateDao);
        }catch(Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }
}
