package com.kalgookso.august.service;

import com.kalgookso.august.entity.article.Article;
import com.kalgookso.august.repository.ArticleRepository;
import com.kalgookso.august.specification.AugustSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class DefaultArticleService implements ArticleService {

    private final ArticleRepository articleRepository;

    public DefaultArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article save(Article article) {
        return this.articleRepository.save(article);
    }

    @Override
    @Deprecated
    public Optional<Article> findById(String id) {
        return this.articleRepository.findOne(AugustSpecification.idEquals(id));
    }

    @Override
    public Article view(String id) {
        return this.articleRepository.findOne(AugustSpecification.idEquals(id))
                .map(Article::increaseViewCount)
                .orElseThrow(() -> new NoSuchElementException("Article not found"));
    }

    @Override
    public Page<Article> findAll(Pageable pageable) {
        return this.articleRepository.findAll(Specification.where(null), pageable);
    }

    @Override
    public void deleteById(String id) {
        this.articleRepository.deleteById(id);
    }

}