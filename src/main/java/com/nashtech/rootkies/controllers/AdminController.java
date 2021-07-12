package com.nashtech.rootkies.controllers;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.constants.SuccessCode;
import com.nashtech.rootkies.converter.CartConverter;
import com.nashtech.rootkies.converter.CategoryConverter;
import com.nashtech.rootkies.converter.ProductConverter;
import com.nashtech.rootkies.converter.UserConverter;
import com.nashtech.rootkies.dto.cart.response.CartDTO;
import com.nashtech.rootkies.dto.category.request.CreateCategoryDTO;
import com.nashtech.rootkies.dto.category.request.UpdateCategoryDTO;
import com.nashtech.rootkies.dto.category.response.CategoryDTO;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.dto.product.request.CreateProductDTO;
import com.nashtech.rootkies.dto.product.request.SearchProductDTO;
import com.nashtech.rootkies.dto.product.request.UpdateProductDTO;
import com.nashtech.rootkies.dto.product.response.ProductDTO;
import com.nashtech.rootkies.dto.user.UserDTO;
import com.nashtech.rootkies.dto.user.request.CreateUserDTO;
import com.nashtech.rootkies.exception.*;
import com.nashtech.rootkies.model.Category;
import com.nashtech.rootkies.model.Product;
import com.nashtech.rootkies.service.CartService;
import com.nashtech.rootkies.service.CategoryService;
import com.nashtech.rootkies.service.ProductService;
import com.nashtech.rootkies.service.UserService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@Api( tags = "Admin")
public class AdminController {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    CategoryConverter categoryConverter;
    @Autowired
    ProductConverter productConverter;
    @Autowired
    CartConverter cartConverter;
    @Autowired
    UserConverter userConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @PostMapping("/products/search")
    public ResponseEntity<ResponseDTO> searchProducts(@Valid @RequestBody SearchProductDTO searchDto) throws DataNotFoundException {
        ResponseDTO response = new ResponseDTO();
        try{
            Page<ProductDTO> products = productService.search(searchDto);
            response.setData(products);
        }catch(Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    /**
     *
     * @param  productDto
     * @return ResponseDTO
     * @throws ParseException
     */
    @PostMapping("/products")
    public ResponseEntity<ResponseDTO> createProduct(@Valid @RequestBody CreateProductDTO productDto) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try{
            Product createdProduct = productService.create(productConverter.convertToEntity(productDto));
            response.setData(createdProduct);
        }catch(Exception ex){
            response.setErrorCode(ErrorCode.ERR_CREATE_PRODUCT_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_PRODUCT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    /**
     *
     * @param id
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<ResponseDTO> findProduct(@PathVariable("id") Long id) throws DataNotFoundException {
        ResponseDTO response = new ResponseDTO();
        try{
            Optional<Product> foundProduct = productService.findById(id);
            ProductDTO responseProduct = modelMapper.map(foundProduct.get() , ProductDTO.class);
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

    @PutMapping("/products")
    public ResponseEntity<ResponseDTO> updateProduct(@Valid @RequestBody UpdateProductDTO dto) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try{
            Product updatedProduct = productService.update(modelMapper.map(dto , Product.class));
            response.setData(modelMapper.map(updatedProduct , ProductDTO.class));
        }catch(Exception ex){
            response.setErrorCode(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }


    @DeleteMapping("/products/{id}")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable("id") Long id) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try{
            boolean isDeleted = productService.delete(id);
            response.setData(isDeleted);
        }catch(Exception ex){
            throw new DeleteDataFailException(ex.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    /**
     *
     * @param cateId
     * @return
     * @throws DataNotFoundException
     */
    @ResponseBody
    @GetMapping("/categories/{cateId}")
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

    /**
     *
     * @param cateDto
     * @return
     */
    @PostMapping("/categories")
    public ResponseEntity<ResponseDTO> createCategory(@Valid @RequestBody CreateCategoryDTO cateDto) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try{
            Category category = categoryService.create(modelMapper.map(cateDto , Category.class));
            response.setData(category);
        }catch(Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_CATEGORY_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    /**
     *
     * @param updateCateDto
     * @return
     */
    @PutMapping("/categories")
    public ResponseEntity<ResponseDTO> updateCategory(@Valid @RequestBody UpdateCategoryDTO updateCateDto) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try{
            Category updatedCate = categoryService.update(modelMapper.map(updateCateDto , Category.class));
            response.setData(updatedCate);
        } catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_CATEGORY_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    /**
     *
     * @param cateId
     * @return
     */
    @DeleteMapping("/categories/{cateId}")
    public ResponseEntity<ResponseDTO> deleteCate(@PathVariable("cateId") Long cateId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try{
            boolean isDeleted = categoryService.delete(cateId);
            response.setData(isDeleted);
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_CATEGORY_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    /**
     *
     * @param id
     * @return
     * @throws DataNotFoundException
     */
    @GetMapping("/carts/{id}")
    public ResponseEntity<ResponseDTO> getCart(@PathVariable("id") Long id) throws DataNotFoundException {
        ResponseDTO response = new ResponseDTO();
        try{
            response.setData(cartService.getCart(id));
        }catch(Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_CART_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    /**
     *
     * @param cartDto
     * @return
     * @throws UpdateDataFailException
     */
    @PutMapping("/carts")
    public ResponseEntity<ResponseDTO> updateCart(@Valid @RequestBody CartDTO cartDto) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try{
            response.setData(cartService.update(cartConverter.convertCartDtoToEntity(cartDto)));
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_CART_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    /**
     *
     * @param id
     * @return
     * @throws UserNotFoundException
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable Long id) throws UserNotFoundException {
        ResponseDTO response = new ResponseDTO();

        try{
            UserDTO user = userConverter.convertToDTO(userService.getUser(id));
            response.setData(user);
        }catch(Exception ex){
            throw new UserNotFoundException(ErrorCode.ERR_USER_NOT_FOUND);
        }

        return ResponseEntity.ok().body(response);
    }

    /**
     *
     * @param dto
     * @return
     * @throws CreateDataFailException
     */
    @PostMapping("/users")
    public ResponseEntity<ResponseDTO> createUser(@Valid @RequestBody CreateUserDTO dto)
            throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try{
            response.setData(userService.createUser(userConverter.convertToEntity(dto)));
            response.setSuccessCode(SuccessCode.USER_CREATED_SUCCESS);
            return ResponseEntity.ok().body(response);
        }catch(Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_USER_FAIL);
        }
    }


}
