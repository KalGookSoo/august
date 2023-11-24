package com.kalgookso.august.repository;

import com.kalgookso.august.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AttachmentRepository extends JpaRepository<Attachment, String>, JpaSpecificationExecutor<Attachment> {
}