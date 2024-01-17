package com.kalgookso.august.service.lms;

import com.kalgookso.august.entity.lms.Enrollment;
import com.kalgookso.august.repository.lms.CourseRepository;
import com.kalgookso.august.repository.lms.EnrollmentRepository;
import com.kalgookso.august.specification.lms.CourseSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultEnrollmentService implements EnrollmentService {

    private final CourseRepository courseRepository;

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public DefaultEnrollmentService(CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    /**
     * 강좌에 학생을 등록하는 메서드입니다.
     * @param courseId  강좌 식별자입니다.
     * @param studentId 학생 식별자입니다.
     * @return 수강신청 정보입니다.
     */
    @Override
    public Enrollment enrollStudentInCourse(String courseId, String studentId) {
        if (courseRepository.exists(CourseSpecification.idEquals(courseId))) {
            Enrollment pendingEnrollment = Enrollment.createPendingEnrollment(courseId, studentId);
            return enrollmentRepository.save(pendingEnrollment);
        } else {
            throw new IllegalArgumentException("Course with id " + courseId + " not found");
        }
    }
}