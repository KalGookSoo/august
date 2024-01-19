package com.kalgookso.august.lms.query;

import com.kalgookso.august.lms.entity.Professor;
import org.springframework.data.jpa.domain.Specification;

public class ProfessorSpecification {

    public static Specification<Professor> nameContains(String name) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }

}