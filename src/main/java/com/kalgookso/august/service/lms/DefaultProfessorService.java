package com.kalgookso.august.service.lms;

import com.kalgookso.august.entity.lms.*;
import com.kalgookso.august.repository.lms.CourseRepository;
import com.kalgookso.august.repository.lms.ProfessorRepository;
import com.kalgookso.august.specification.lms.ProfessorSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultProfessorService implements ProfessorService {
    private final ProfessorRepository professorRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public DefaultProfessorService(ProfessorRepository professorRepository, CourseRepository courseRepository) {
        this.professorRepository = professorRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Professor create(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public Optional<Professor> findById(String id) {
        return professorRepository.findById(id);
    }

    @Override
    public List<Professor> findAllByName(String name) {
        Specification<Professor> specification = Specification.where(null);
        if (name != null) {
            specification = specification.and(ProfessorSpecification.nameContains(name));
        }
        return professorRepository.findAll(specification);
    }

    /**
     * 교수를 제거하는 메서드입니다.
     *
     * @param id 제거할 교수의 식별자입니다.
     */
    @Override
    public void remove(String id) {
        professorRepository.deleteById(id);
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    /**
     * 강좌를 제거하는 메서드입니다.
     *
     * @param courseId 제거할 강좌의 식별자입니다.
     */
    @Override
    public void removeCourse(String courseId) {
        courseRepository.deleteById(courseId);
    }

    /**
     * 교수에게 전공을 추가하는 메서드입니다.
     *
     * @param id 전공을 추가할 교수의 식별자입니다.
     * @param major 교수에게 추가할 전공입니다.
     * @throws IllegalArgumentException 교수 식별자가 데이터베이스에 없을 경우 예외를 발생시킵니다.
     */
    @Override
    public void addMajor(String id, Major major) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor with id " + id + " not found"));
        professor.addMajor(major);
    }

    /**
     * 강좌에 학생을 추가하는 메서드입니다.
     *
     * @param courseId 학생을 추가할 강좌의 식별자입니다.
     * @param student 강좌에 추가할 학생 객체입니다.
     * @throws IllegalArgumentException 강좌 식별자가 데이터베이스에 없을 경우 예외를 발생시킵니다.
     */
    @Override
    public void addStudentToCourse(String courseId, Student student) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course with id " + courseId + " not found"));
        course.addStudent(student);
    }

    /**
     * 강좌에서 학생을 제거하는 메서드입니다.
     *
     * @param courseId 학생을 제거할 강좌의 식별자입니다.
     * @param student  강좌에서 제거할 학생 객체입니다.
     * @throws IllegalArgumentException 강좌 식별자가 데이터베이스에 없을 경우 예외를 발생시킵니다.
     */
    @Override
    public void removeStudentFromCourse(String courseId, Student student) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course with id " + courseId + " not found"));
        course.removeStudent(student);
    }
}