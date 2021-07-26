package com.nashtech.rootkies.repository.specs;

import com.nashtech.rootkies.dto.category.request.SearchCategoryDTO;
import com.nashtech.rootkies.model.Category;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategorySpecification {

    public Specification<Category> getCategories(SearchCategoryDTO request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getText() != null && !request.getText().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + request.getText() + "%"));
            }
            query.orderBy(criteriaBuilder.desc(root.get(request.getSortBy())));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
