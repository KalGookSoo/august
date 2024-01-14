package com.kalgookso.august.repository.lms;

import com.kalgookso.august.entity.lms.Enrollment;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface EnrollmentRepository extends Repository<Enrollment, String> {
    Enrollment save(Enrollment enrollment);
    Optional<Enrollment> findById(String id);
    Optional<Enrollment> findOne(Specification<Enrollment> specification);
    void deleteById(String id);
}