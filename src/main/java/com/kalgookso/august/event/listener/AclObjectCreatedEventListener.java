package com.kalgookso.august.event.listener;

import com.kalgookso.august.event.AclObjectCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;
import org.springframework.stereotype.Component;

@Component
public class AclObjectCreatedEventListener {

    private final MutableAclService mutableAclService;

    public AclObjectCreatedEventListener(MutableAclService mutableAclService) {
        this.mutableAclService = mutableAclService;
    }

    @Async
    @EventListener
    public void handleAclObjectCreatedEvent(AclObjectCreatedEvent event) {

        // 객체에 대한 ACL 객체 식별자를 생성합니다.
        ObjectIdentity objectId = new ObjectIdentityImpl(event.getJavaType(), event.getIdentifier());
        MutableAcl acl = mutableAclService.createAcl(objectId);

        // createdBy 사용자에 대한 SID를 생성합니다.
        Sid ownerSid = new PrincipalSid(event.getPrincipal());

        // createdBy 사용자에게 객체에 대한 관리 권한을 부여합니다.
        acl.insertAce(acl.getEntries().size(), BasePermission.ADMINISTRATION, ownerSid, true);

        // 데이터베이스의 ACL을 업데이트합니다.
        mutableAclService.updateAcl(acl);
    }

}