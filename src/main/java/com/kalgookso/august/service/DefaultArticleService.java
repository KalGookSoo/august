package com.kalgookso.august.service;

import com.kalgookso.august.criteria.ArticleCriteria;
import com.kalgookso.august.entity.article.Article;
import com.kalgookso.august.entity.article.Attachment;
import com.kalgookso.august.entity.article.Comment;
import com.kalgookso.august.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static com.kalgookso.august.specification.ArticleSpecification.categoryIdEquals;
import static com.kalgookso.august.specification.ArticleSpecification.idEquals;

@Service
@Transactional
public class DefaultArticleService implements ArticleService {

    private final ArticleRepository articleRepository;

    public DefaultArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void deleteById(String id) {
        articleRepository.deleteById(id);
    }

    @Override
    public Page<Article> findByCategoryId(String categoryId, ArticleCriteria criteria, Pageable pageable) {
        return articleRepository.findAll(categoryIdEquals(categoryId), pageable);
    }

    @Override
    public Article view(String id) {
        return articleRepository.findOne(idEquals(id))
                .map(Article::increaseViewCount)
                .orElseThrow(() -> new NoSuchElementException("Article not found"));
    }

    @Override
    public void addAttachment(String articleId, Attachment attachment) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NoSuchElementException("Article not found"));
        article.addAttachment(attachment);
    }

    @Override
    public void removeAttachment(String articleId, String attachmentId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NoSuchElementException("Article not found"));
        article.removeAttachmentById(attachmentId);
    }

    @Override
    public void addComment(String articleId, Comment comment) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NoSuchElementException("Article not found"));
        article.addComment(comment);
    }

    @Override
    public void removeComment(String articleId, String commentId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NoSuchElementException("Article not found"));
        article.removeCommentById(commentId);
    }

}