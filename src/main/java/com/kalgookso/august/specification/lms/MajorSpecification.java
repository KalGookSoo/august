package com.kalgookso.august.specification.lms;

import com.kalgookso.august.entity.lms.Major;
import org.springframework.data.jpa.domain.Specification;

public class MajorSpecification {

    public static Specification<Major> nameContains(String name) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }

}