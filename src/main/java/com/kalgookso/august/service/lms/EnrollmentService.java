package com.kalgookso.august.service.lms;

import com.kalgookso.august.entity.lms.Enrollment;

/**
 * 수강신청 서비스입니다.
 */
public interface EnrollmentService {
    Enrollment enrollStudentInCourse(String courseId, String studentId);
}