package com.kalgookso.august.service.lms;

import com.kalgookso.august.entity.lms.Enrollment;
import com.kalgookso.august.repository.lms.CourseRepository;
import com.kalgookso.august.repository.lms.EnrollmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class EnrollmentServiceTest {

    private EnrollmentService enrollmentService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    private Enrollment testEnrollment;

    @BeforeEach
    public void setup() {
        enrollmentService = new DefaultEnrollmentService(courseRepository, enrollmentRepository);
        // TODO 수강신청을 하려면 강좌와 학생이 있어야 합니다.
        // TODO 강좌와 학생에 대한 연관관계가 있기 때문에 의존성을 어떻게 해결하여 단위 테스트할 지 생각해 봅시다.
//        testEnrollment = enrollmentService.enrollStudentInCourse("courseId", "studentId");
    }

}