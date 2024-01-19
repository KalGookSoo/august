package com.kalgookso.august.lms.service;

import com.kalgookso.august.lms.entity.Enrollment;

/**
 * 수강신청 서비스입니다.
 */
public interface EnrollmentService {
    Enrollment enrollStudentInCourse(Long courseId, Long studentId);
}