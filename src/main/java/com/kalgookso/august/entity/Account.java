package com.kalgookso.august.entity;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_account")
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings("unused")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String username;

    private String password;

    private String name;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Authority> authorities = new HashSet<>();

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
        authority.setAccount(this);
    }

    public void removeAuthority(Authority authority) {
        this.authorities.remove(authority);
        authority.setAccount(null);
    }

    public Account(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }
    public Account(String username, Authority authority) {
        this.username = username;
        this.addAuthority(authority);
    }
}
