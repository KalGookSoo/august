package com.kalgookso.august.entity.article;

import com.kalgookso.august.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 게시글
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "tb_article")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class Article extends BaseEntity {

    /**
     * 제목
     */
    private String title;

    /**
     * 본문
     */
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * 카테고리 식별자
     */
    @JoinColumn(name = "category_id")
    private String categoryId;

    /**
     * 조회수
     */
    private int viewCount;

    /**
     * 첨부파일 목록
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "article_id")
    private final List<Attachment> attachments = new ArrayList<>();

    /**
     * 댓글 목록
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "article_id")
    private final List<Comment> comments = new ArrayList<>();

    /**
     * 소유자
     */
    @CreatedBy
    private String createdBy;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Article increaseViewCount() {
        this.viewCount++;
        return this;
    }

    public Article addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
        return this;
    }

    public Article addComment(Comment comment) {
        this.comments.add(comment);
        return this;
    }

    public Article removeAttachmentById(String attachmentId) {
        this.attachments.removeIf(attachment -> attachment.getId().equals(attachmentId));
        return this;
    }

    public Article removeCommentById(String commentId) {
        this.comments.removeIf(comment -> comment.getId().equals(commentId));
        return this;
    }

}