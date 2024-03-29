package com.kalgookso.august.lms.entity;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 수강신청
 * 수강신청은 강좌와 학생 사이에 1 개만 존재할 수 있습니다.
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "tb_enrollment")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class Enrollment {

    @Id
    @GeneratedValue
    private Long id;

    private Long courseId;

    private Long studentId;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    @CreatedDate
    private LocalDateTime registeredAt;

    /**
     * 강좌 ID를 인자로 받아 새로운 Enrollment 객체를 생성하고 초기 상태를 PENDING으로 설정하는 팩토리 메서드입니다.
     *
     * @param courseId 수강신청을 원하는 강좌의 ID입니다.
     * @param studentId 수강신청을 원하는 학생의 ID입니다.
     * @param enrollmentStatus 수강신청의 초기 상태입니다. 이 값은 EnrollmentStatus 열거형의 값 중 하나여야 합니다.
     * @return 초기 상태가 PENDING인 Enrollment 객체를 반환합니다.
     */
    public static Enrollment create(Long courseId, Long studentId, EnrollmentStatus enrollmentStatus) {
        Enrollment enrollment = new Enrollment();
        enrollment.courseId = courseId;
        enrollment.studentId = studentId;
        enrollment.status = enrollmentStatus;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }
}