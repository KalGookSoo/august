package com.kalgookso.august.lms.repository;

import com.kalgookso.august.lms.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MajorRepository extends JpaRepository<Major, Long>, JpaSpecificationExecutor<Major> {
}