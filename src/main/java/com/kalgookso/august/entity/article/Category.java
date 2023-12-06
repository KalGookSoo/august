package com.kalgookso.august.entity.article;

import com.kalgookso.august.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "tb_category")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class Category extends BaseEntity {

    private String name;

    @CreatedBy
    private String createdBy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

}