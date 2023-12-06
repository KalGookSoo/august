package com.kalgookso.august.specification;

import com.kalgookso.august.entity.article.Article;
import org.springframework.data.jpa.domain.Specification;

public class ArticleSpecification {

    public static Specification<Article> idEquals(String id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Article> categoryIdEquals(String categoryId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("categoryId"), categoryId);
    }

}