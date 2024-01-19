package com.kalgookso.august.cms.event;

import org.springframework.context.ApplicationEvent;

public class AclObjectCreatedEvent extends ApplicationEvent {

    private final Class<?> javaType;

    private final String identifier;

    private final String principal;

    public AclObjectCreatedEvent(Class<?> javaType, String identifier, String principal) {
        super(identifier);
        this.javaType = javaType;
        this.identifier = identifier;
        this.principal = principal;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getPrincipal() {
        return principal;
    }

}