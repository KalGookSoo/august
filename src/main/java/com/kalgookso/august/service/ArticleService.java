package com.kalgookso.august.service;

import com.kalgookso.august.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * 이 인터페이스는 게시글 서비스를 정의합니다.
 * 게시글 생성, 조회, 삭제 등의 기능을 제공합니다.
 */
public interface ArticleService {

    /**
     * 주어진 게시글을 저장하고 반환합니다.
     * @param article 저장할 게시글
     * @return 저장된 게시글
     */
    Article save(Article article);

    /**
     * 주어진 ID에 해당하는 게시글을 조회합니다.
     * @param id 조회할 게시글의 ID
     * @return 조회된 게시글. 해당 ID의 게시글이 없을 경우 빈 Optional 반환
     */
    Optional<Article> findById(String id);

    /**
     * 주어진 ID에 해당하는 게시글을 조회하고 조회수를 증가시킵니다.
     * @param id 조회할 게시글의 ID
     * @return 조회된 게시글
     */
    Article view(String id);

    /**
     * 모든 게시글을 페이지 단위로 조회합니다.
     * @param pageable 페이지 정보
     * @return 조회된 게시글 페이지
     */
    Page<Article> findAll(Pageable pageable);

    /**
     * 주어진 ID에 해당하는 게시글을 삭제합니다.
     * @param id 삭제할 게시글의 ID
     */
    void deleteById(String id);

}