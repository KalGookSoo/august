package com.kalgookso.august.cms.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 기본 엔티티 클래스입니다.
 * 이 클래스는 모든 엔티티 클래스에서 공통으로 사용하는 필드와 메서드를 정의합니다.
 */
@MappedSuperclass
@SuppressWarnings("unused")
public abstract class BaseEntity {

    /**
     * 엔티티 식별자입니다.
     * UUID를 사용하여 고유한 값을 가집니다.
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(updatable = false)
    private String id;

    /**
     * 엔티티가 생성된 시간입니다.
     * 엔티티가 데이터베이스에 처음 저장될 때의 시간을 자동으로 저장합니다.
     */
    @CreatedDate
    private LocalDateTime createdAt;

    /**
     * 엔티티가 마지막으로 수정된 시간입니다.
     * 엔티티가 데이터베이스에 저장될 때마다 시간을 자동으로 업데이트합니다.
     */
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    /**
     * 엔티티의 식별자를 반환하는 메서드입니다.
     * @return 엔티티의 식별자
     */
    public String getId() {
        return id;
    }

    /**
     * 엔티티의 식별자를 설정하는 메서드입니다.
     * @param id 엔티티의 식별자
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 엔티티가 생성된 시간을 반환하는 메서드입니다.
     * @return 엔티티가 생성된 시간
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * 엔티티가 생성된 시간을 설정하는 메서드입니다.
     * @param createdAt 엔티티가 생성된 시간
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 엔티티가 마지막으로 수정된 시간을 반환하는 메서드입니다.
     * @return 엔티티가 마지막으로 수정된 시간
     */
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    /**
     * 엔티티가 마지막으로 수정된 시간을 설정하는 메서드입니다.
     * @param modifiedAt 엔티티가 마지막으로 수정된 시간
     */
    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

}