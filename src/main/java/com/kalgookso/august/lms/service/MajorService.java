package com.kalgookso.august.lms.service;

import com.kalgookso.august.lms.entity.Major;

import java.util.List;
import java.util.Optional;

public interface MajorService {
    Major create(Major major);
    Optional<Major> findById(Long id);
    List<Major> findAllByName(String name);
    void remove(Long id);
}