package com.nashtech.rootkies.controllers;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.constants.SuccessCode;
import com.nashtech.rootkies.converter.*;
import com.nashtech.rootkies.dto.category.request.CreateCategoryDTO;
import com.nashtech.rootkies.dto.category.request.SearchCategoryDTO;
import com.nashtech.rootkies.dto.category.request.UpdateCategoryDTO;
import com.nashtech.rootkies.dto.category.response.CategoryDTO;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.exception.*;
import com.nashtech.rootkies.model.Category;
import com.nashtech.rootkies.service.*;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@Api( tags = "Admin")
public class AdminController {

    /*@Autowired
    ModelMapper modelMapper;
    @Autowired
    CategoryService categoryService;
    @Autowired
    BrandService brandService;
    @Autowired
    UserService userService;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    CategoryConverter categoryConverter;
    @Autowired
    UserConverter userConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @ResponseBody
    @GetMapping("/categories/{cateId}")
    public ResponseEntity<ResponseDTO> getCategory(@PathVariable("cateId") Long cateId) throws DataNotFoundException {
        ResponseDTO response = new ResponseDTO();
        Optional<Category> category = categoryService.findCategory(cateId);
        CategoryDTO cateDao = categoryConverter.convertEntityToDTO(category.get());
        response.setData(cateDao);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/categories")
    public ResponseEntity<ResponseDTO> createCategory(@Valid @RequestBody CreateCategoryDTO cateDto)
            throws UpdateDataFailException, DataNotFoundException, DuplicateDataException {
        ResponseDTO response = new ResponseDTO();

        if(categoryService.checkExist(cateDto.getName())){
            throw new DuplicateDataException(ErrorCode.ERR_CATEGORY_EXISTED);
        }
        if(!StringUtils.isEmpty(cateDto.getParentId())){
            Optional<Category> parentCate = categoryService.findCategory(cateDto.getParentId());
            if(!parentCate.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_PARENT_CATEGORY_NOT_EXISTS);
            }
        }
        Boolean saved = categoryService.create(categoryConverter.convertToEntity(cateDto));
        response.setData(saved);
        response.setSuccessCode(SuccessCode.CATEGORY_CREATED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/categories")
    public ResponseEntity<ResponseDTO> updateCategory(@Valid @RequestBody UpdateCategoryDTO updateCateDto)
            throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        Category updatedCate = categoryService.update(modelMapper.map(updateCateDto , Category.class));
        response.setData(updatedCate);
        return ResponseEntity.ok().body(response);
    }


    @DeleteMapping("/categories/{cateId}")
    public ResponseEntity<ResponseDTO> deleteCate(@PathVariable("cateId") Long cateId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        boolean isDeleted = categoryService.delete(cateId);
        response.setData(isDeleted);
        response.setSuccessCode(SuccessCode.CATEGORY_DELETED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/categories/search")
    public ResponseEntity<ResponseDTO> search(@Valid @RequestBody SearchCategoryDTO searchRequest){
        ResponseDTO response = new ResponseDTO();
        response.setData(categoryService.search(searchRequest));
        response.setSuccessCode(SuccessCode.CATEGORIES_LOADED_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/brands")
    public ResponseEntity<ResponseDTO> createBrand(@Valid @RequestBody CreateBrandDTO dto)
                    throws DataNotFoundException, UpdateDataFailException, InvalidRequestDataException {
        ResponseDTO response = new ResponseDTO();
        response.setData(brandService.create(dto));
        response.setSuccessCode(SuccessCode.CREATE_BRAND_SUCCESS);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/organizations")
    public ResponseEntity<ResponseDTO> createOrganization(@Valid @RequestBody CreateOrganizationDTO dto)
                        throws DuplicateDataException, UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        response.setData(organizationService.create(dto));
        response.setSuccessCode(SuccessCode.CREATE_ORGANIZATION_SUCCESS);
        return ResponseEntity.ok().body(response);
    }*/
}
