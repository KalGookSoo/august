package com.kalgookso.august.entity.lms;

/**
 * 수강신청의 상태를 나타내는 열거형입니다.
 */
public enum EnrollmentStatus {
    /**
     * 수강신청이 대기 중인 상태입니다. 학생이 수강신청을 했지만 아직 승인되지 않은 상태를 나타냅니다.
     */
    PENDING,

    /**
     * 수강신청이 완료된 상태입니다. 학생의 수강신청이 승인된 상태를 나타냅니다.
     */
    COMPLETED,

    /**
     * 수강신청이 취소된 상태입니다. 학생이 수강신청을 취소하거나, 수강신청이 거절된 상태를 나타냅니다.
     */
    CANCELED
}