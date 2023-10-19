package com.kalgookso.august.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

/**
 * 계정 모델
 */
@Relation(collectionRelation = "accounts", itemRelation = "account")
public class AccountModel extends RepresentationModel<AccountModel> {

    /**
     * 계정 식별자
     */
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
    private LocalDateTime createdAt;

    /**
     * 수정 일시
     */
    private LocalDateTime modifiedAt;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets created at.
     *
     * @return the created at
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets modified at.
     *
     * @return the modified at
     */
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    /**
     * Instantiates a new Account model.
     *
     * @param id         the id
     * @param username   the username
     * @param password   the password
     * @param name       the name
     * @param createdAt  the created at
     * @param modifiedAt the modified at
     */
    public AccountModel(String id, String username, String password, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

}