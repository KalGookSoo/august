package com.kalgookso.august.cms.event;

import org.springframework.context.ApplicationEvent;

public class AclObjectDeletedEvent extends ApplicationEvent {

    private final Class<?> javaType;

    private final String identifier;

    public AclObjectDeletedEvent(Class<?> javaType, String identifier) {
        super(identifier);
        this.javaType = javaType;
        this.identifier = identifier;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public String getIdentifier() {
        return identifier;
    }

}