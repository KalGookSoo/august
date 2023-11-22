package com.kalgookso.august.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 계정
 */
@Entity
@Table(name = "tb_account")
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@SuppressWarnings({"unused", "JpaDataSourceORMInspection"})
public class Account {

    /**
     * 계정 식별자
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

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

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Authority> authorities = new LinkedHashSet<>();

    /**
     * 생성 일시
     */
    @CreatedDate
    private LocalDateTime createdAt;

    /**
     * 수정 일시
     */
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    /**
     * 계정명을 변경하고 자신을 반환합니다.
     * @param password 패스워드
     * @return 계정
     */
    @SuppressWarnings("UnusedReturnValue")
    public Account changePassword(String password) {
        this.password = password;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

}