package com.kalgookso.august.lms.service;

import com.kalgookso.august.lms.entity.Course;
import com.kalgookso.august.lms.entity.Enrollment;
import com.kalgookso.august.lms.entity.EnrollmentStatus;
import com.kalgookso.august.lms.query.LmsSpecification;
import com.kalgookso.august.lms.repository.CourseRepository;
import com.kalgookso.august.lms.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
     * 강좌 정보를 조회하여 수강신청 정보를 생성합니다.
     * @param courseId  강좌 식별자입니다.
     * @param studentId 학생 식별자입니다.
     * @return 수강신청 정보입니다.
     */
    @Override
    public Enrollment enrollStudentInCourse(Long courseId, Long studentId) {
        Optional<Course> foundCourse = courseRepository.findOne(LmsSpecification.idEquals(courseId));
        if (foundCourse.isPresent()) {
            Course course = foundCourse.get();
            long enrollmentCountByCourseId = enrollmentRepository.count(LmsSpecification.courseIdEquals(courseId));
            EnrollmentStatus enrollmentStatus = course.verifyCapacity(enrollmentCountByCourseId) ? EnrollmentStatus.PENDING : EnrollmentStatus.COMPLETED;
            return enrollmentRepository.save(Enrollment.create(courseId, studentId, enrollmentStatus));
        } else {
            throw new IllegalArgumentException("Course with id " + courseId + " not found");
        }
    }
}