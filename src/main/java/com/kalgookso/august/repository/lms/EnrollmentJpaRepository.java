package com.kalgookso.august.repository.lms;

import com.kalgookso.august.entity.lms.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

public class EnrollmentJpaRepository implements EnrollmentRepository {

    private final EntityManager entityManager;

    @Autowired
    public EnrollmentJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        entityManager.persist(enrollment);
        return enrollment;
    }

    @Override
    public Optional<Enrollment> findById(String id) {
        Enrollment enrollment = entityManager.find(Enrollment.class, id);
        return Optional.ofNullable(enrollment);
    }

    @Override
    public Optional<Enrollment> findOne(Specification<Enrollment> specification) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Enrollment> criteriaQuery = criteriaBuilder.createQuery(Enrollment.class);
        Root<Enrollment> root = criteriaQuery.from(Enrollment.class);
        Predicate predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder);
        criteriaQuery.where(predicate);
        Enrollment enrollment = entityManager.createQuery(criteriaQuery).getSingleResult();
        return Optional.ofNullable(enrollment);
    }

    @Override
    public void deleteById(String id) {
        Enrollment enrollment = entityManager.find(Enrollment.class, id);
        if (enrollment != null) {
            entityManager.remove(enrollment);
        }
    }
}