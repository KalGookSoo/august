package com.kalgookso.august.service.lms;

import com.kalgookso.august.entity.lms.Course;
import com.kalgookso.august.entity.lms.Enrollment;
import com.kalgookso.august.entity.lms.Student;
import com.kalgookso.august.repository.lms.CourseRepository;
import com.kalgookso.august.repository.lms.EnrollmentRepository;
import com.kalgookso.august.repository.lms.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultStudentService implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public DefaultStudentService(StudentRepository studentRepository, CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void addEnrollment(String studentId, String courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student with id " + studentId + " not found"));
        Enrollment pendingEnrollment = Enrollment.createPendingEnrollment(courseId);
        student.addEnrollment(pendingEnrollment);
    }

    @Override
    public void registerCourse(String studentId, String courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student with id " + studentId + " not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course with id " + courseId + " not found"));
        student.addCourse(course);
    }
}