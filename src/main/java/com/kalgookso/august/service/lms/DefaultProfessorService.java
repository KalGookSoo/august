package com.kalgookso.august.service.lms;

import com.kalgookso.august.entity.lms.Course;
import com.kalgookso.august.entity.lms.Professor;
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
     * 강좌를 생성하는 메서드입니다.
     *
     * @param professorId 강좌를 개설할 교수의 식별자입니다.
     * @param courseName 생성할 강좌의 이름입니다.
     * @param majorName 강좌의 전공 이름입니다.
     * @return 생성된 강좌 객체를 반환합니다.
     * @throws IllegalArgumentException 교수 식별자가 데이터베이스에 없을 경우 예외를 발생시킵니다.
     */
    @Override
    public Course createCourse(String professorId, String courseName, String majorName) {
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new IllegalArgumentException("Professor with id " + professorId + " not found"));
        Course course = professor.createCourse(courseName, majorName);
        return courseRepository.save(course);
    }
}