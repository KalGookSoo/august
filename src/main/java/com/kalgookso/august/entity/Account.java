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
    private String username;

    /**
     * 패스워드
     */
    private String password;

    /**
     * 이름
     */
    private String name;

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
     * 권한 목록
     */
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Authority> authorities;

    public Account() {

    }

    /**
     * 권한 추가
     */
    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
        authority.setAccount(this);
    }

    /**
     * 권한 제거
     */
    public void removeAuthority(Authority authority) {
        this.authorities.remove(authority);
        authority.setAccount(null);
    }

    /**
     * 생성자
     *
     * @param username 계정명
     * @param password 패스워드
     * @param name     이름
     */
    public Account(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    /**
     * 생성자
     *
     * @param username  계정명
     * @param authority 권한
     */
    public Account(String username, Authority authority) {
        this.username = username;
        this.addAuthority(authority);
    }

    public String getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return this.modifiedAt;
    }

    public Set<Authority> getAuthorities() {
        if (this.authorities == null) {
            this.authorities = new LinkedHashSet<>();
        }
        return this.authorities;
    }

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

}