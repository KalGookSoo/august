package com.kalgookso.august.entity;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 메뉴
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "tb_menu")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class Menu extends BaseEntity {

    private String name;

    private String url;

    @CreatedBy
    private String createdBy;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "parent_id")
    private List<Menu> children = new ArrayList<>();

    public void addChild(Menu child) {
        children.add(child);
    }

    public void removeChild(Menu child) {
        children.remove(child);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

}