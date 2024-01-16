package com.kalgookso.august.entity.lms;

import com.kalgookso.august.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 수강신청
 * 수강신청은 강좌와 수강생 사이에 1 개만 존재할 수 있습니다.
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "tb_enrollment")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class Enrollment extends BaseEntity {

    private String courseId;

    private String studentId;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    public String getCourseId() {
        return courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    /**
     * 강좌 ID를 인자로 받아 새로운 Enrollment 객체를 생성하고 초기 상태를 PENDING으로 설정하는 팩토리 메서드입니다.
     *
     * @param courseId 수강신청을 원하는 강좌의 ID입니다.
     * @return 초기 상태가 PENDING인 Enrollment 객체를 반환합니다.
     */
    public static Enrollment createPendingEnrollment(String courseId) {
        Enrollment enrollment = new Enrollment();
        enrollment.courseId = courseId;
        enrollment.status = EnrollmentStatus.PENDING;
        return enrollment;
    }

    /**
     * 수강신청의 상태를 변경하는 메서드입니다.
     *
     * @param newStatus 변경하려는 새로운 상태입니다. 이 값은 EnrollmentStatus 열거형의 값 중 하나여야 합니다.
     */
    public void changeStatus(EnrollmentStatus newStatus) {
        this.status = newStatus;
    }

    /**
     * 수강신청이 승인되었는지 확인하는 메서드입니다.
     *
     * @return 수강신청의 상태가 COMPLETED인 경우 true를 반환하고, 그렇지 않은 경우 false를 반환합니다.
     */
    public boolean isApproved() {
        return this.status == EnrollmentStatus.COMPLETED;
    }
}