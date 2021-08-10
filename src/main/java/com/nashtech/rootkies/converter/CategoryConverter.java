package com.nashtech.rootkies.converter;

import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    /*@Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepository categoryRepository;

    public CategoryDTO convertEntityToDTO(Category cate){
        CategoryDTO dto = modelMapper.map(cate , CategoryDTO.class);
        return dto;
    }

    public Category convertToEntity(CreateCategoryDTO cateDto) {
        Category cate = new Category(cateDto.getName() , cateDto.getDescription());
        Category parent = null;
        if(!StringUtils.isEmpty(cateDto.getParentId())){
            parent = categoryRepository.findById(cateDto.getParentId()).get();
            parent.getSubCategories().add(cate);
        }else{

            return cate;
        }
        return parent;
    }

    public List<CategoryDTO> convertToListDTO(List<Category> cates) {
        return cates.stream().map(c -> convertEntityToDTO(c)).collect(Collectors.toList());
    }*/
}
