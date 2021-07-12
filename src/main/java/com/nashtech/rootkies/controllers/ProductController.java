package com.nashtech.rootkies.controllers;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.converter.ProductConverter;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.dto.product.response.ProductDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.exception.ResourceNotFoundException;
import com.nashtech.rootkies.model.Product;
import com.nashtech.rootkies.service.ProductService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Api( tags = "Product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductConverter productConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    /**
     *
     * @param id
     * @return
     * @throws DataNotFoundException
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findProduct(@PathVariable("id") Long id) throws DataNotFoundException {
        ResponseDTO response = new ResponseDTO();
        try{
            Optional<Product> foundProduct = productService.findById(id);
            ProductDTO responseProduct = productConverter.convertToDTO(foundProduct.get());
            responseProduct.setCategoryId(foundProduct.get().getCategory().getId());
            if(foundProduct.isPresent()){
                response.setData(responseProduct);
            }
        }catch(Exception ex){
            response.setErrorCode(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }
}
