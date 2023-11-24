package com.kalgookso.august.entity;

import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "tb_category")
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