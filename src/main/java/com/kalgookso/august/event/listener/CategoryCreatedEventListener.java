package com.kalgookso.august.event.listener;

import com.kalgookso.august.entity.Category;
import com.kalgookso.august.event.CategoryCreatedEvent;
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

/**
 * 카테고리 생성 이벤트 리스너 클래스입니다.
 * 이 클래스는 카테고리 생성 이벤트를 처리하며, 이벤트가 발생하면 ACL(Access Control List)에 카테고리 정보를 추가합니다.
 */
@Component
public class CategoryCreatedEventListener {

    private final MutableAclService mutableAclService;  // MutableAclService 인스턴스

    /**
     * CategoryCreatedEventListener 생성자입니다.
     * @param mutableAclService MutableAclService 인스턴스
     */
    public CategoryCreatedEventListener(MutableAclService mutableAclService) {
        this.mutableAclService = mutableAclService;
    }

    /**
     * 카테고리 생성 이벤트를 처리하는 메서드입니다.
     * 이 메서드는 비동기적으로 실행되며, 카테고리 생성 이벤트가 발생하면 ACL에 카테고리 정보를 추가하고, 생성된 카테고리의 소유자에게 관리 권한을 부여합니다.
     * @param event 카테고리 생성 이벤트
     */
    @Async
    @EventListener
    public void handleCategoryCreatedEvent(CategoryCreatedEvent event) {
        Category category = event.getCategory();

        // 카테고리에 대한 ACL 객체 식별자를 생성합니다.
        ObjectIdentity objectId = new ObjectIdentityImpl(Category.class, category.getId());
        MutableAcl acl = mutableAclService.createAcl(objectId);

        // createdBy 사용자에 대한 SID를 생성합니다.
        Sid ownerSid = new PrincipalSid(category.getCreatedBy());

        // createdBy 사용자에게 카테고리에 대한 관리 권한을 부여합니다.
        acl.insertAce(acl.getEntries().size(), BasePermission.ADMINISTRATION, ownerSid, true);

        // 데이터베이스의 ACL을 업데이트합니다.
        mutableAclService.updateAcl(acl);
    }

}