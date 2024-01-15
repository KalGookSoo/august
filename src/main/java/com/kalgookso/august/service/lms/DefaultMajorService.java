package com.kalgookso.august.service.lms;

import com.kalgookso.august.entity.lms.Major;
import com.kalgookso.august.repository.lms.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}