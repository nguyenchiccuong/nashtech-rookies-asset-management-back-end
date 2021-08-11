package com.nashtech.rootkies.repository.specs;

import com.nashtech.rootkies.model.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserSpecificationBuilder {

    private final List<SearchCriteria1> params;

    public UserSpecificationBuilder() {
        params = new ArrayList<SearchCriteria1>();
    }

    public UserSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria1(key, operation, value));
        return this;
    }

    public Specification<User> build() {
        if (params.size() == 0) {
            return null;
        }


        List<Specification> specs = params.stream()
                .map(UserSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {

            result = Specification.where(result)
                    .and(specs.get(i));

        }
        return result;
    }
}
