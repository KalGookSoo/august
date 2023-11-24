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

/**
 * 기본 첨부 파일 서비스 클래스입니다.
 * 이 클래스는 AttachmentService 인터페이스를 구현하며, AttachmentCommandRepository와 AttachmentQueryRepository를 사용하여 첨부 파일 관련 작업을 수행합니다.
 */
@Service
@Transactional
public class DefaultAttachmentService implements AttachmentService {

    private final AttachmentRepository attachmentRepository;

    /**
     * DefaultAttachmentService 생성자입니다.
     *
     * @param attachmentRepository 첨부 파일 저장소
     */
    public DefaultAttachmentService(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    /**
     * 첨부 파일을 저장하는 메서드입니다.
     * @param attachment 저장할 첨부 파일
     * @return 저장된 첨부 파일
     */
    @Override
    public Attachment save(Attachment attachment) {
        return this.attachmentRepository.save(attachment);
    }

    /**
     * ID로 첨부 파일을 찾는 메서드입니다.
     * @param id 첨부 파일 ID
     * @return 찾은 첨부 파일 (Optional)
     */
    @Override
    public Optional<Attachment> findById(String id) {
        return this.attachmentRepository.findOne(AugustSpecification.idEquals(id));
    }

    /**
     * 모든 첨부 파일을 페이지로 반환하는 메서드입니다.
     * @param pageable 페이지 정보
     * @return 첨부 파일 페이지
     */
    @Override
    public Page<Attachment> findAll(Pageable pageable) {
        return this.attachmentRepository.findAll(Specification.where(null), pageable);
    }

    /**
     * ID로 첨부 파일을 삭제하는 메서드입니다.
     * @param id 삭제할 첨부 파일의 ID
     */
    @Override
    public void deleteById(String id) {
        this.attachmentRepository.deleteById(id);
    }

}