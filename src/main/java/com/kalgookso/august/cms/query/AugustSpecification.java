package com.kalgookso.august.cms.query;

import org.springframework.data.jpa.domain.Specification;

public class AugustSpecification<T> {

    public static <T> Specification<T> idEquals(String id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

}