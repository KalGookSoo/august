package com.kalgookso.august.repository.lms;

import com.kalgookso.august.entity.lms.Course;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CourseRepository extends Repository<Course, String> {
    Course save(Course course);
    Optional<Course> findById(String id);
    Optional<Course> findOne(Specification<Course> specification);
    void deleteById(String id);
}