package com.kalgookso.august.event.listener;

import com.kalgookso.august.entity.Category;
import com.kalgookso.august.event.CategoryDeletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.stereotype.Component;

/**
 * 카테고리 삭제 이벤트 리스너 클래스입니다.
 * 이 클래스는 카테고리 삭제 이벤트를 처리하며, 이벤트가 발생하면 ACL에서 카테고리 정보를 삭제합니다.
 */
@Component
public class CategoryDeletedEventListener {

    private final MutableAclService mutableAclService;  // MutableAclService 인스턴스

    /**
     * CategoryDeletedEventListener 생성자입니다.
     * @param mutableAclService MutableAclService 인스턴스
     */
    public CategoryDeletedEventListener(MutableAclService mutableAclService) {
        this.mutableAclService = mutableAclService;
    }

    /**
     * 카테고리 삭제 이벤트를 처리하는 메서드입니다.
     * 이 메서드는 카테고리 삭제 이벤트가 발생하면 ACL에서 카테고리 정보를 삭제합니다.
     * @param event 카테고리 삭제 이벤트
     */
    @Async
    @EventListener
    public void handleCategoryDeletedEvent(CategoryDeletedEvent event) {
        Category category = event.getCategory();

        // 카테고리에 대한 ACL 객체 식별자를 생성합니다.
        ObjectIdentity objectId = new ObjectIdentityImpl(Category.class, category.getId());

        // 데이터베이스의 ACL에서 카테고리 정보를 삭제합니다.
        mutableAclService.deleteAcl(objectId, true);
    }

}