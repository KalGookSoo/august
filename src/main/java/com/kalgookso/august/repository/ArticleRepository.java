package com.kalgookso.august.repository;

import com.kalgookso.august.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleRepository extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {
}