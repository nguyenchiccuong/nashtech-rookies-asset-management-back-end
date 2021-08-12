package com.nashtech.rootkies.repository.specs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.nashtech.rootkies.model.Assignment;

import org.springframework.data.jpa.domain.Specification;

public class AssignmentSpecification implements Specification<Assignment> {

    private List<SearchCriteria> list;

    public AssignmentSpecification() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<Assignment> arg0, CriteriaQuery<?> arg1, CriteriaBuilder arg2) {
        // TODO Auto-generated method stub
        return null;
    }

}
