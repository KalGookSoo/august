package com.kalgookso.august.lms.service;

import com.kalgookso.august.lms.entity.Major;
import com.kalgookso.august.lms.query.LmsSpecification;
import com.kalgookso.august.lms.repository.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultMajorService implements MajorService {

    private final MajorRepository majorRepository;

    @Autowired
    public DefaultMajorService(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    @Override
    public Major create(Major major) {
        return majorRepository.save(major);
    }

    @Override
    public Optional<Major> findById(Long id) {
        return majorRepository.findById(id);
    }

    @Override
    public List<Major> findAllByName(String name) {
        Specification<Major> specification = Specification.where(null);
        if (name != null) {
            specification = specification.and(LmsSpecification.nameContains(name));
        }
        return majorRepository.findAll(specification);
    }

    @Override
    public void remove(Long id) {
        majorRepository.deleteById(id);
    }

}