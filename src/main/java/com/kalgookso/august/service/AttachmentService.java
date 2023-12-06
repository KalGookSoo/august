package com.kalgookso.august.service;

import com.kalgookso.august.entity.Attachment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AttachmentService {

    Attachment save(Attachment attachment);

    Optional<Attachment> findById(String id);

    Page<Attachment> findAll(Pageable pageable);

    void deleteById(String id);

}