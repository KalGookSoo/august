package com.kalgookso.august.repository;

import com.kalgookso.august.entity.Article;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

public interface ArticleQueryRepository extends Repository<Article, String>, JpaSpecificationExecutor<Article> {

}