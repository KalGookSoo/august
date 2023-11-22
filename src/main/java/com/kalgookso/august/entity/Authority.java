package com.kalgookso.august.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 권한
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "tb_authority")
public class Authority {

    /**
     * 권한 식별자
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    /**
     * 이름
     */
    private String name;

    /**
     * 계정 식별자
     */
    private String accountId;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

}