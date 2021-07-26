package com.nashtech.rootkies.service.impl;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.converter.BrandConverter;
import com.nashtech.rootkies.dto.brand.request.CreateBrandDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.exception.InvalidRequestDataException;
import com.nashtech.rootkies.exception.UpdateDataFailException;
import com.nashtech.rootkies.repository.BrandRepository;
import com.nashtech.rootkies.repository.CategoryRepository;
import com.nashtech.rootkies.repository.OrganizationRepository;
import com.nashtech.rootkies.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BrandConverter brandConverter;

    @Override
    public Boolean checkExistById(Long brandId) {
        return brandRepository.existsById(brandId);
    }

    @Override
    public Boolean create(CreateBrandDTO dto) throws UpdateDataFailException, InvalidRequestDataException, DataNotFoundException {

        try{
            if(StringUtils.isEmpty(dto.getName())){
                throw new InvalidRequestDataException(ErrorCode.ERR_BRAND_NAME_EMPTY);
            }
            if(brandRepository.existsByName(dto.getName())) {
                throw new InvalidRequestDataException(ErrorCode.ERR_BRAND_EXISTED);
            }
            if(!dto.getCategories().stream().allMatch(c -> categoryRepository.existsById(c))){
                    throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_IDS_NOT_CORRECT);
                }
            if(dto.getOrganizationId() != null){
                if(!organizationRepository.existsById(dto.getOrganizationId())){
                        throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
                }
            }
            brandRepository.save(brandConverter.convertToEntity(dto));
            return true;
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_CREATE_BRAND_FAIL);
        }

    }
}
