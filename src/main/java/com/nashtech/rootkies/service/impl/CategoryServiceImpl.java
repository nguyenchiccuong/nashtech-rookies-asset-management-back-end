package com.nashtech.rootkies.service.impl;

import com.nashtech.rootkies.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    /*@Autowired
    CategoryConverter categoryConverter;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategorySpecification categorySpecification;

    @Override
    public Boolean create(Category cate) throws UpdateDataFailException {
        try {
            return (null != categoryRepository.save(cate)) ? true : false;
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
    }

    @Override
    public Optional<Category> findCategory(Long cateId) throws DataNotFoundException {
        Optional<Category> cateOp = categoryRepository.findById(cateId);
        if(!cateOp.isPresent()){
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        return cateOp;
    }

    @Override
    public Category update(Category cate) throws UpdateDataFailException {
        try{
            cate.setUpdatedDate(LocalDateTime.now());
            return categoryRepository.save(cate);
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_CATEGORY_FAIL);
        }

    }

    @Override
    public Boolean delete(Long cateId) throws DeleteDataFailException {
        try {
            Optional<Category> opCate = categoryRepository.findById(cateId);
            if(!opCate.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
            }
            if(opCate.get().getSubCategories().size() > 0){
                throw new DeleteDataFailException(ErrorCode.ERR_HAVE_SUB_CATEGORIES);
            }
            categoryRepository.deleteById(cateId);
            return categoryRepository.findById(cateId).isPresent() ? false : true;
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_CATEGORY_FAIL);
        }
    }

    @Override
    public boolean checkExist(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public boolean checkExistById(Long categoryId) {
        return categoryRepository.existsById(categoryId);
    }

    @Override
    public Page<CategoryDTO> search(SearchCategoryDTO request) {
        Pageable pageRequest = PageRequest.of(request.getPage() , request.getSize() , Sort.by(request.getSortBy()));
        Page<Category> catesPaged = categoryRepository.findAll(categorySpecification.getCategories(request) , pageRequest);
        Page<CategoryDTO> pages = new PageImpl<>(categoryConverter.convertToListDTO(catesPaged.getContent())
                , pageRequest, catesPaged.getContent().size());
        return pages;
    }*/
}
