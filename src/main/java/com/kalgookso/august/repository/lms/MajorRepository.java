package com.kalgookso.august.repository.lms;

import com.kalgookso.august.entity.lms.Major;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MajorRepository extends JpaRepository<Major, String>, JpaSpecificationExecutor<Major> {
}