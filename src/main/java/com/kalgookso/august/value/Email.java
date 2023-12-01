package com.kalgookso.august.value;

import javax.persistence.Embeddable;

@Embeddable
public class Email {

    private String id;

    private String domain;

    protected Email() {
    }

    public Email(String id, String domain) {
        this.id = id;
        this.domain = domain;
    }

    public String getId() {
        return id;
    }

    public String getDomain() {
        return domain;
    }

    public String getValue() {
        return id + "@" + domain;
    }

}