package com.kalgookso.august.service;

import com.kalgookso.august.criteria.ArticleCriteria;
import com.kalgookso.august.entity.article.Article;
import com.kalgookso.august.entity.article.Attachment;
import com.kalgookso.august.entity.article.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {

    Article save(Article article);

    void deleteById(String id);

    Page<Article> findByCategoryId(String categoryId, ArticleCriteria criteria, Pageable pageable);

    Article view(String id);

    Article addAttachment(String articleId, Attachment attachment);

    Article removeAttachment(String articleId, String attachmentId);

    Article addComment(String articleId, Comment comment);

}