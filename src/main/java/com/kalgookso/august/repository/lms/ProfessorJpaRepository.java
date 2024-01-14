package com.kalgookso.august.repository.lms;

import com.kalgookso.august.entity.lms.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

public class ProfessorJpaRepository implements ProfessorRepository {

    private final EntityManager entityManager;

    @Autowired
    public ProfessorJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Professor save(Professor professor) {
        entityManager.persist(professor);
        return professor;
    }

    @Override
    public Optional<Professor> findById(String id) {
        Professor professor = entityManager.find(Professor.class, id);
        return Optional.ofNullable(professor);
    }

    @Override
    public Optional<Professor> findOne(Specification<Professor> specification) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Professor> criteriaQuery = criteriaBuilder.createQuery(Professor.class);
        Root<Professor> root = criteriaQuery.from(Professor.class);
        Predicate predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder);
        criteriaQuery.where(predicate);
        Professor professor = entityManager.createQuery(criteriaQuery).getSingleResult();
        return Optional.ofNullable(professor);
    }

    @Override
    public void deleteById(String id) {
        Professor professor = entityManager.find(Professor.class, id);
        if (professor != null) {
            entityManager.remove(professor);
        }
    }
}