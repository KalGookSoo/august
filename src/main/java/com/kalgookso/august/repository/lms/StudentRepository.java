package com.kalgookso.august.repository.lms;

import com.kalgookso.august.entity.lms.Student;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface StudentRepository extends Repository<Student, String> {
    Student save(Student student);
    Optional<Student> findById(String id);
    Optional<Student> findOne(Specification<Student> specification);
    void deleteById(String id);
}