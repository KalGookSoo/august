package com.kalgookso.august.service;

import com.kalgookso.august.entity.Attachment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * 첨부 파일 서비스 인터페이스입니다.
 * 이 인터페이스는 첨부 파일 관련 작업을 정의합니다.
 */
public interface AttachmentService {

    /**
     * 첨부 파일를 저장하는 메서드입니다.
     * @param attachment 저장할 첨부 파일
     * @return 저장된 첨부 파일
     */
    Attachment save(Attachment attachment);

    /**
     * ID로 첨부 파일를 찾는 메서드입니다.
     * @param id 첨부 파일 ID
     * @return 찾은 첨부 파일 (Optional)
     */
    Optional<Attachment> findById(String id);

    /**
     * 모든 첨부 파일를 페이지로 반환하는 메서드입니다.
     * @param pageable 페이지 정보
     * @return 첨부 파일 페이지
     */
    Page<Attachment> findAll(Pageable pageable);

    /**
     * ID로 첨부 파일를 삭제하는 메서드입니다.
     * @param id 삭제할 첨부 파일의 ID
     */
    void deleteById(String id);

}