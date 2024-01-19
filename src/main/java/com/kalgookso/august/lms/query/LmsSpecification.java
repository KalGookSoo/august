package com.kalgookso.august.lms.query;

import org.springframework.data.jpa.domain.Specification;

public class LmsSpecification<T> {

    public static <T> Specification<T> idEquals(Long id) {
        return (root, query, builder) -> builder.equal(root.get("id"), id);
    }

    public static <T> Specification<T> courseIdEquals(Long courseId) {
        return (root, query, builder) -> builder.equal(root.get("courseId"), courseId);
    }

    public static <T> Specification<T> nameContains(String name) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }

}