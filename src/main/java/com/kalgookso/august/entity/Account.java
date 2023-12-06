package com.kalgookso.august.entity;

import com.kalgookso.august.value.ContactNumber;
import com.kalgookso.august.value.Email;
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
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@SuppressWarnings({"unused", "JpaDataSourceORMInspection"})
public class Account extends BaseEntity {

    /**
     * 계정명
     */
    @Column(unique = true, nullable = false)
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
     * 이메일
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "email_id")),
            @AttributeOverride(name = "domain", column = @Column(name = "email_domain"))
    })
    private Email email;

    /**
     * 연락처
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "first", column = @Column(name = "contact_number_first")),
            @AttributeOverride(name = "middle", column = @Column(name = "contact_number_middle")),
            @AttributeOverride(name = "last", column = @Column(name = "contact_number_last"))
    })
    private ContactNumber contactNumber;

    /**
     * 권한 목록
     */
    @OneToMany(mappedBy = "accountId", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Authority> authorities = new LinkedHashSet<>();

    public Account changePassword(String password) {
        Assert.notNull(password, "Password must not be null");
        this.password = password;
        return this;
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

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public ContactNumber getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(ContactNumber contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

}