package com.kalgookso.august.service;

import com.kalgookso.august.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * 카테고리 서비스 인터페이스입니다.
 * 이 인터페이스는 카테고리 관련 작업을 정의합니다.
 */
public interface CategoryService {

    /**
     * 카테고리를 저장하는 메서드입니다.
     * @param category 저장할 카테고리
     * @return 저장된 카테고리
     */
    Category save(Category category);

    /**
     * ID로 카테고리를 찾는 메서드입니다.
     * @param id 카테고리 ID
     * @return 찾은 카테고리 (Optional)
     */
    Optional<Category> findById(String id);

    /**
     * 모든 카테고리를 페이지로 반환하는 메서드입니다.
     * @param pageable 페이지 정보
     * @return 카테고리 페이지
     */
    Page<Category> findAll(Pageable pageable);

    /**
     * ID로 카테고리를 삭제하는 메서드입니다.
     * @param id 삭제할 카테고리의 ID
     */
    void deleteById(String id);

}