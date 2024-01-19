package com.kalgookso.august.lms.query;

import com.kalgookso.august.lms.entity.Course;
import org.springframework.data.jpa.domain.Specification;

public class CourseSpecification {

    public static Specification<Course> idEquals(String id) {
        return (root, query, builder) -> builder.equal(root.get("id"), id);
    }

}