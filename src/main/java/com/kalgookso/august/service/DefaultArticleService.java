package com.kalgookso.august.service;

import com.kalgookso.august.entity.Article;
import com.kalgookso.august.repository.ArticleRepository;
import com.kalgookso.august.specification.AugustSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 기본 게시글 서비스 클래스입니다.
 * 이 클래스는 ArticleService 인터페이스를 구현하며, ArticleCommandRepository와 ArticleQueryRepository를 사용하여 게시글 관련 작업을 수행합니다.
 */
@Service
@Transactional
public class DefaultArticleService implements ArticleService {

    private final ArticleRepository articleRepository;

    /**
     * DefaultArticleService 생성자입니다.
     *
     * @param articleRepository 게시글 저장소
     */
    public DefaultArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /**
     * 게시글를 저장하는 메서드입니다.
     * @param article 저장할 게시글
     * @return 저장된 게시글
     */
    @Override
    public Article save(Article article) {
        return this.articleRepository.save(article);
    }

    /**
     * ID로 게시글를 찾는 메서드입니다.
     * @param id 게시글 ID
     * @return 찾은 게시글 (Optional)
     */
    @Override
    public Optional<Article> findById(String id) {
        return this.articleRepository.findOne(AugustSpecification.idEquals(id));
    }

    /**
     * 모든 게시글를 페이지로 반환하는 메서드입니다.
     * @param pageable 페이지 정보
     * @return 게시글 페이지
     */
    @Override
    public Page<Article> findAll(Pageable pageable) {
        return this.articleRepository.findAll(Specification.where(null), pageable);
    }

    /**
     * ID로 게시글를 삭제하는 메서드입니다.
     * @param id 삭제할 게시글의 ID
     */
    @Override
    public void deleteById(String id) {
        this.articleRepository.deleteById(id);
    }

}