package com.nashtech.rootkies.converter;

import com.nashtech.rootkies.dto.brand.request.CreateBrandDTO;
import com.nashtech.rootkies.model.Brand;
import com.nashtech.rootkies.model.Category;
import com.nashtech.rootkies.model.Organization;
import com.nashtech.rootkies.repository.CategoryRepository;
import com.nashtech.rootkies.repository.OrganizationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BrandConverter {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Brand convertToEntity(CreateBrandDTO dto){
        Brand brand = modelMapper.map(dto , Brand.class);
        Organization org = organizationRepository.findById(dto.getOrganizationId()).get();
        brand.setOrganization(org);
        Set<Category> cates = dto.getCategories().stream()
                .map(c -> categoryRepository.findById(c).get()).collect(Collectors.toSet());
        brand.setCategories(cates);
        return brand;
    }
}
