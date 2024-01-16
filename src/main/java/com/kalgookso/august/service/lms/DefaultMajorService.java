package com.kalgookso.august.service.lms;

import com.kalgookso.august.entity.lms.Major;
import com.kalgookso.august.repository.lms.MajorRepository;
import com.kalgookso.august.specification.lms.MajorSpecification;
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
    public Optional<Major> findById(String id) {
        return majorRepository.findById(id);
    }

    @Override
    public List<Major> findAllByName(String name) {
        Specification<Major> specification = Specification.where(null);
        if (name != null) {
            specification = specification.and(MajorSpecification.nameContains(name));
        }
        return majorRepository.findAll(specification);
    }

}