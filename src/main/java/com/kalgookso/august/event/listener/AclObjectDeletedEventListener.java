package com.kalgookso.august.event.listener;

import com.kalgookso.august.event.AclObjectDeletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.stereotype.Component;

@Component
public class AclObjectDeletedEventListener {

    private final MutableAclService mutableAclService;

    public AclObjectDeletedEventListener(MutableAclService mutableAclService) {
        this.mutableAclService = mutableAclService;
    }

    @EventListener
    public void handleAclObjectDeletedEvent(AclObjectDeletedEvent event) {
        ObjectIdentityImpl objectId = new ObjectIdentityImpl(event.getJavaType(), event.getIdentifier());
        MutableAcl acl = (MutableAcl) mutableAclService.readAclById(objectId);
        for (int i = acl.getEntries().size() - 1; i >= 0; i--) {
            acl.deleteAce(i);
        }
        mutableAclService.updateAcl(acl);
    }
}