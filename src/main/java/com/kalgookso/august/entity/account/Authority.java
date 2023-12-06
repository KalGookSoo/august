package com.kalgookso.august.entity.account;

import com.kalgookso.august.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 권한
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "tb_authority")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class Authority extends BaseEntity {

    /**
     * 이름
     */
    private String name;

    /**
     * 생성자
     *
     * @param name 이름
     */
    public Authority(String name) {
        this.name = name;
    }

    protected Authority() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}