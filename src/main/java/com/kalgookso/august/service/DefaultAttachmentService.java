package com.kalgookso.august.service;

import com.kalgookso.august.entity.Attachment;
import com.kalgookso.august.repository.AttachmentRepository;
import com.kalgookso.august.specification.AugustSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DefaultAttachmentService implements AttachmentService {

    private final AttachmentRepository attachmentRepository;

    public DefaultAttachmentService(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public Attachment save(Attachment attachment) {
        return this.attachmentRepository.save(attachment);
    }

    @Override
    public Optional<Attachment> findById(String id) {
        return this.attachmentRepository.findOne(AugustSpecification.idEquals(id));
    }

    @Override
    public Page<Attachment> findAll(Pageable pageable) {
        return this.attachmentRepository.findAll(Specification.where(null), pageable);
    }

    @Override
    public void deleteById(String id) {
        this.attachmentRepository.deleteById(id);
    }

}