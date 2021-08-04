package com.nashtech.rootkies.converter;

import org.springframework.stereotype.Component;

@Component
public class BrandConverter {

    /*@Autowired
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
    }*/
}
