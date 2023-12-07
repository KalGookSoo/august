package com.kalgookso.august.entity.article;

import com.kalgookso.august.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 첨부파일
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "tb_attachment")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class Attachment extends BaseEntity {

    private String name;

    private String pathName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

}