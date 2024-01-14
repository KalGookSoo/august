package com.kalgookso.august.repository.lms;

import com.kalgookso.august.entity.lms.Major;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface MajorRepository extends Repository<Major, String> {
    Major save(Major major);
    Optional<Major> findById(String id);
    Optional<Major> findOne(Specification<Major> specification);
    void deleteById(String id);
}