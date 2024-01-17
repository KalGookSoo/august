package com.kalgookso.august.specification.lms;

import com.kalgookso.august.entity.lms.Course;
import org.springframework.data.jpa.domain.Specification;

public class CourseSpecification {

    public static Specification<Course> idEquals(String id) {
        return (root, query, builder) -> builder.equal(root.get("id"), id);
    }

}