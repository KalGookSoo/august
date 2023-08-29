package com.kalgookso.august.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_authority")
@Setter
@Getter
public class Authority {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public Authority(String name) {
        this.name = name;
    }

    public void setAccount(Account account) {
        this.account = account;
        account.getAuthorities().add(this);
    }

    protected Authority() {
    }
}
