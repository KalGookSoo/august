package com.kalgookso.august.repository.lms;

import com.kalgookso.august.entity.lms.Professor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ProfessorRepository extends Repository<Professor, String> {
    Professor save(Professor professor);
    Optional<Professor> findById(String id);
    Optional<Professor> findOne(Specification<Professor> specification);
    void deleteById(String id);
}