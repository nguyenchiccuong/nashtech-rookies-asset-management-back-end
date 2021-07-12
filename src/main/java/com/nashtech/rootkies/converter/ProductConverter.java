package com.nashtech.rootkies.converter;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.dto.product.request.CreateProductDTO;
import com.nashtech.rootkies.dto.product.response.ProductDTO;
import com.nashtech.rootkies.exception.ConvertEntityDTOException;
import com.nashtech.rootkies.model.Brand;
import com.nashtech.rootkies.model.Category;
import com.nashtech.rootkies.model.Product;
import com.nashtech.rootkies.repository.BrandRepository;
import com.nashtech.rootkies.repository.CategoryRepository;
import com.nashtech.rootkies.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BrandRepository brandRepository;

    public ProductDTO convertToDTO(Product product) throws ConvertEntityDTOException {
        try {
            ProductDTO dto = modelMapper.map(product, ProductDTO.class);
            dto.setCategoryId(product.getCategory().getId());
            return dto;
        }catch(Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    /**
     *
     * @param dto
     * @return
     */
    public Product convertToEntity(CreateProductDTO dto) throws ConvertEntityDTOException {
        Product product = modelMapper.map(dto, Product.class);
        try {
            Category category = categoryRepository.findById(dto.getCategoryId()).get();
            Brand brand = brandRepository.findById(dto.getBrandId()).get();
            product.setCategory(category);
        }catch(Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return product;
    }
}
