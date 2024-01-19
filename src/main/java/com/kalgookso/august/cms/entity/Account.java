package com.kalgookso.august.cms.entity;

import com.kalgookso.august.cms.value.ContactNumber;
import com.kalgookso.august.cms.value.Email;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
            @AttributeOverride(name = "first", column = @Column(name = "first_contact_number")),
            @AttributeOverride(name = "middle", column = @Column(name = "middle_contact_number")),
            @AttributeOverride(name = "last", column = @Column(name = "last_contact_number"))
    })
    private ContactNumber contactNumber;

    /**
     * 권한 목록
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "account_id")
    private final List<Authority> authorities = new ArrayList<>();

    /**
     * 만료 일시
     */
    private LocalDateTime expiredAt;

    /**
     * 잠금 일시
     */
    private LocalDateTime lockedAt;

    /**
     * 패스워드 만료 일시
     */
    private LocalDateTime credentialsExpiredAt;

    public void changePassword(String password) {
        Assert.notNull(password, "Password must not be null");
        this.password = password;
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

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public LocalDateTime getLockedAt() {
        return lockedAt;
    }

    public LocalDateTime getCredentialsExpiredAt() {
        return credentialsExpiredAt;
    }

    /**
     * 만료 일시는 금일(00:00)로부터 2년 후 까지로 설정합니다.
     * 패스워드 만료 일시는 생성일(00:00)로부터 180일 후 까지로 설정합니다.
     */
    public void initializeAccountPolicy() {
        expiredAt = LocalDate.now().atTime(LocalTime.MIDNIGHT).plusYears(2L);
        credentialsExpiredAt = LocalDate.now().atTime(LocalTime.MIDNIGHT).plusDays(180L);
    }

}