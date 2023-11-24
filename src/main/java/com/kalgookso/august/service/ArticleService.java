package com.kalgookso.august.service;

import com.kalgookso.august.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * 게시글 서비스 인터페이스입니다.
 * 이 인터페이스는 게시글 관련 작업을 정의합니다.
 */
public interface ArticleService {

    /**
     * 게시글를 저장하는 메서드입니다.
     * @param article 저장할 게시글
     * @return 저장된 게시글
     */
    Article save(Article article);

    /**
     * ID로 게시글를 찾는 메서드입니다.
     * @param id 게시글 ID
     * @return 찾은 게시글 (Optional)
     */
    Optional<Article> findById(String id);

    /**
     * 모든 게시글를 페이지로 반환하는 메서드입니다.
     * @param pageable 페이지 정보
     * @return 게시글 페이지
     */
    Page<Article> findAll(Pageable pageable);

    /**
     * ID로 게시글를 삭제하는 메서드입니다.
     * @param id 삭제할 게시글의 ID
     */
    void deleteById(String id);

}