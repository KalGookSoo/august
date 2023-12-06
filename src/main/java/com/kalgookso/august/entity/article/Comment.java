package com.kalgookso.august.entity.article;

import com.kalgookso.august.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 댓글
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "tb_comment")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class Comment extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreatedBy
    private String createdBy;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

}