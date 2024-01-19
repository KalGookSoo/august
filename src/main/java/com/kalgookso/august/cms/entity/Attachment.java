package com.kalgookso.august.cms.entity;

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

    private String mimeType;

    private long size;

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

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getSize() {
        return size;
    }

}