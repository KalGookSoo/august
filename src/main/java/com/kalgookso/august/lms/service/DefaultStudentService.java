package com.kalgookso.august.lms.service;

import com.kalgookso.august.lms.entity.Student;
import com.kalgookso.august.lms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultStudentService implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public DefaultStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

}