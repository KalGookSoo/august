package com.kalgookso.august.service.lms;

import com.kalgookso.august.entity.lms.Major;

import java.util.List;
import java.util.Optional;

public interface MajorService {
    Major create(Major major);
    Optional<Major> findById(String id);
    List<Major> findAllByName(String name);
}