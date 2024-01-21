package com.kalgookso.august.cms.service;

import com.kalgookso.august.cms.command.ArticleCommand;
import com.kalgookso.august.cms.query.ArticleCriteria;
import com.kalgookso.august.cms.entity.Article;
import com.kalgookso.august.cms.entity.Attachment;
import com.kalgookso.august.cms.entity.Comment;
import com.kalgookso.august.cms.event.AclObjectCreatedEvent;
import com.kalgookso.august.cms.exception.FileWriteException;
import com.kalgookso.august.cms.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.kalgookso.august.cms.query.ArticleSpecification.categoryIdEquals;
import static com.kalgookso.august.cms.query.ArticleSpecification.idEquals;

/**
 * TODO 게시글을 생성할 때 카테고리의 타입이 공개인 경우 BasePermission을 BasePermission.ADMINISTRATION으로 설정합니다.
 * TODO 게시글을 생성할 때 카테고리의 타입이 비공개인 경우 READ 현재 인증 주체가 해당 아티클에 READ 권한이 있는 경우에만 조회할 수 있도록 합니다.
 */
@Service
@Transactional
public class DefaultArticleService implements ArticleService {

    private final ArticleRepository articleRepository;

    private final ApplicationEventPublisher eventPublisher;

    private final String uploadPath;

    public DefaultArticleService(ArticleRepository articleRepository, ApplicationEventPublisher eventPublisher, @Value("${com.kalgookso.august.filepath}") String uploadPath) {
        this.articleRepository = articleRepository;
        this.eventPublisher = eventPublisher;
        this.uploadPath = uploadPath;
    }

    @Override
    public Article create(ArticleCommand command) {
        Article article = new Article();
        article.setTitle(command.getTitle());
        article.setContent(command.getContent());
        try {
            addAttachments(article, command.getMultipartFiles());
        } catch (FileWriteException e) {
            throw new FileWriteException("Failed to write file: " + e.getMessage());
        }
        Article savedArticle = articleRepository.save(article);
        eventPublisher.publishEvent(new AclObjectCreatedEvent(Article.class, savedArticle.getId(), savedArticle.getCreatedBy()));
        return savedArticle;
    }

    @Override
    public Article update(String id, ArticleCommand command) {
        Article article = articleRepository.findOne(idEquals(id))
                .orElseThrow(() -> new NoSuchElementException("Article not found"));
        article.setTitle(command.getTitle());
        article.setContent(command.getContent());
        try {
            addAttachments(article, command.getMultipartFiles());
        } catch (FileWriteException e) {
            throw new FileWriteException("Failed to write file: " + e.getMessage());
        }
        return article;
    }

    @Override
    public void deleteById(String id) {
        Article article = articleRepository.findOne(idEquals(id))
                .orElseThrow(() -> new NoSuchElementException("Article not found"));
        removeAttachments(article, article.getAttachments().stream().map(Attachment::getId).collect(Collectors.toList()));
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

    private void addAttachments(Article article, List<MultipartFile> multipartFiles) {
        for (MultipartFile multipartFile : multipartFiles) {
            try {
                Attachment attachment = new Attachment();
                attachment.setName(multipartFile.getOriginalFilename());
                attachment.setMimeType(multipartFile.getContentType());
                attachment.setSize(multipartFile.getSize());
                article.addAttachment(attachment);

                byte[] bytes = multipartFile.getBytes();
                String pathname = uploadPath + attachment.getId();
                FileIOService.write(pathname, bytes);
            } catch (IOException e) {
                throw new FileWriteException("Failed to write file: " + e.getMessage());
            }
        }
    }

    private void removeAttachments(Article article, List<String> attachmentIds) {
        for (String attachmentId : attachmentIds) {
            article.removeAttachmentById(attachmentId);
            String pathname = uploadPath + attachmentId;
            FileIOService.delete(pathname);
        }
    }

}