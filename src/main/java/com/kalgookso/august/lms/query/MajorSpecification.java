package com.kalgookso.august.lms.query;

import com.kalgookso.august.lms.entity.Major;
import org.springframework.data.jpa.domain.Specification;

public class MajorSpecification {

    public static Specification<Major> nameContains(String name) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }

}