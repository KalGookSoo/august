package com.kalgookso.august.repository.lms;

import com.kalgookso.august.entity.lms.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProfessorRepository extends JpaRepository<Professor, String>, JpaSpecificationExecutor<Professor> {
}