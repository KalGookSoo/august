package com.kalgookso.august.repository;

import com.kalgookso.august.entity.Attachment;
import org.springframework.data.repository.Repository;

public interface AttachmentCommandRepository extends Repository<Attachment, String> {

    /**
     * 주어진 Attachment 엔티티를 저장합니다.
     *
     * @param attachment 저장할 Attachment 엔티티.
     * @return 저장된 Attachment 엔티티.
     */
    Attachment save(Attachment attachment);

    /**
     * 주어진 ID에 해당하는 Attachment 엔티티를 삭제합니다.
     *
     * @param id 삭제할 Attachment 엔티티의 ID.
     */
    void deleteById(String id);

}