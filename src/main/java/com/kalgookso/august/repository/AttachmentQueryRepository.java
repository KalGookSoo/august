package com.kalgookso.august.repository;

import com.kalgookso.august.entity.Attachment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

public interface AttachmentQueryRepository extends Repository<Attachment, String>, JpaSpecificationExecutor<Attachment> {

}