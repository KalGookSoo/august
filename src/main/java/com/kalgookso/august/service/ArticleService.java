package com.kalgookso.august.service;

import com.kalgookso.august.entity.article.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ArticleService {

    Article save(Article article);

    Optional<Article> findById(String id);

    Article view(String id);

    Page<Article> findAll(Pageable pageable);

    void deleteById(String id);

}