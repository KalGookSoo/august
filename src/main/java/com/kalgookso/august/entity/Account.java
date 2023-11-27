package com.kalgookso.august.entity;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 계정
 */
@Entity
@Table(name = "tb_account")
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Access(AccessType.FIELD)
@SuppressWarnings({"unused", "JpaDataSourceORMInspection"})
public class Account extends BaseEntity {

    /**
     * 계정명
     */
    @Column(unique = true)
    private String username;

    /**
     * 패스워드
     */
    private String password;

    /**
     * 이름
     */
    private String name;

    @OneToMany(mappedBy = "accountId", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Authority> authorities = new LinkedHashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void changePassword(String password) {
        Assert.notNull(password, "Password must not be null");
        this.password = password;
    }

}