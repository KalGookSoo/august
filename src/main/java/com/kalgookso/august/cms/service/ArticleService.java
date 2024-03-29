package com.kalgookso.august.cms.service;

import com.kalgookso.august.cms.command.ArticleCommand;
import com.kalgookso.august.cms.query.ArticleCriteria;
import com.kalgookso.august.cms.entity.Article;
import com.kalgookso.august.cms.entity.Attachment;
import com.kalgookso.august.cms.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

public interface ArticleService {

    Article create(ArticleCommand command);

    @PreAuthorize("hasPermission(#id, 'com.kalgookso.august.cms.entity.Article', 'WRITE')")
    Article update(String id, ArticleCommand command);

    @PreAuthorize("hasPermission(#id, 'com.kalgookso.august.cms.entity.Article', 'DELETE')")
    void deleteById(String id);

    Page<Article> findByCategoryId(String categoryId, ArticleCriteria criteria, Pageable pageable);

    Article view(String id);

    void addAttachment(String articleId, Attachment attachment);

    void removeAttachment(String articleId, String attachmentId);

    void addComment(String articleId, Comment comment);

    void removeComment(String articleId, String commentId);

}