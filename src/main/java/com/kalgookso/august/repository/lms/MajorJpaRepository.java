package com.kalgookso.august.repository.lms;

import com.kalgookso.august.entity.lms.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

public class MajorJpaRepository implements MajorRepository {

    private final EntityManager entityManager;

    @Autowired
    public MajorJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Major save(Major major) {
        entityManager.persist(major);
        return major;
    }

    @Override
    public Optional<Major> findById(String id) {
        Major major = entityManager.find(Major.class, id);
        return Optional.ofNullable(major);
    }

    @Override
    public Optional<Major> findOne(Specification<Major> specification) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Major> criteriaQuery = criteriaBuilder.createQuery(Major.class);
        Root<Major> root = criteriaQuery.from(Major.class);
        Predicate predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder);
        criteriaQuery.where(predicate);
        Major major = entityManager.createQuery(criteriaQuery).getSingleResult();
        return Optional.ofNullable(major);
    }

    @Override
    public void deleteById(String id) {
        Major major = entityManager.find(Major.class, id);
        if (major != null) {
            entityManager.remove(major);
        }
    }
}