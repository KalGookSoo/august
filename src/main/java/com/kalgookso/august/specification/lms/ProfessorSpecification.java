package com.kalgookso.august.specification.lms;

import com.kalgookso.august.entity.lms.Professor;
import org.springframework.data.jpa.domain.Specification;

public class ProfessorSpecification {

    public static Specification<Professor> nameContains(String name) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }

}