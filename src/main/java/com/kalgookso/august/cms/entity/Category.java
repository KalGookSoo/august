package com.kalgookso.august.cms.entity;

import com.kalgookso.august.cms.value.CategoryType;
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

    @Enumerated(EnumType.STRING)
    private CategoryType type;

    @CreatedBy
    private String createdBy;

    protected Category() {

    }

    public static Category create(String name, CategoryType type) {
        Category category = new Category();
        category.name = name;
        category.type = type;
        return category;
    }

    public Category update(String name, CategoryType type) {
        this.name = name;
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryType getType() {
        return type;
    }

    public String getCreatedBy() {
        return createdBy;
    }

}