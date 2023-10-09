package com.kalgookso.august.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * 계정
 */
@Entity
@Table(name = "tb_account")
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings("unused")
public class Account {

    /**
     * 계정 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private Set<Authority> authorities = new HashSet<>();

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
     * @param username  계정명
     * @param authority 권한
     */
    public Account(String username, Authority authority) {
        this.username = username;
        this.addAuthority(authority);
    }
}
