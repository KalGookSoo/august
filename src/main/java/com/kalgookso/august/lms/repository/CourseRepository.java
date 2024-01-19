package com.kalgookso.august.lms.repository;

import com.kalgookso.august.lms.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseRepository extends JpaRepository<Course, String>, JpaSpecificationExecutor<Course> {
}