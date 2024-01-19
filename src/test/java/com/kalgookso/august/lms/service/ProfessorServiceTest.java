package com.kalgookso.august.lms.service;

import com.kalgookso.august.lms.entity.*;
import com.kalgookso.august.lms.repository.CourseRepository;
import com.kalgookso.august.lms.repository.ProfessorRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class ProfessorServiceTest {

    private ProfessorService professorService;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private CourseRepository courseRepository;

    private Professor testProfessor;

    @BeforeEach
    public void setup() {
        professorService = new DefaultProfessorService(professorRepository, courseRepository);
        Major major = new Major();
        major.setName("테스트 학과");
        Professor professor = Professor.createWithMajor("테스트 교수", major);
        testProfessor = professorService.create(professor);
    }

    @Test
    @DisplayName("교수를 생성합니다.")
    void createTest() {
        // Given
        Major major = new Major();
        major.setName("테스트 학과");
        Professor professor = Professor.createWithMajor("테스트 교수", major);

        // When
        Professor created = professorService.create(professor);

        // Then
        assertNotNull(created.getId());
    }

    @Test
    @DisplayName("교수 식별자로 교수를 조회합니다.")
    public void findProfessorByIdTest() {
        // When
        Optional<Professor> foundProfessor = professorService.findById(testProfessor.getId());

        // Then
        Assertions.assertThat(foundProfessor.isPresent()).isTrue();
        Assertions.assertThat(foundProfessor.get().getName()).isEqualTo("테스트 교수");
    }

    @Test
    @DisplayName("교수명으로 교수 목록을 조회합니다.")
    public void findProfessorByNameTest() {
        // When
        List<Professor> professors = professorService.findAllByName("테스트 교수");

        // Then
        Assertions.assertThat(professors.isEmpty()).isFalse();
        List<String> names = professors.stream().map(Professor::getName).collect(Collectors.toList());
        Assertions.assertThat(names).contains("테스트 교수");
    }

    @Test
    @DisplayName("교수를 제거합니다.")
    public void removeProfessorTest() {
        // When
        professorService.remove(testProfessor.getId());

        // Then
        Optional<Professor> foundProfessor = professorService.findById(testProfessor.getId());
        Assertions.assertThat(foundProfessor.isPresent()).isFalse();
    }

    @Test
    @DisplayName("교수가 강좌를 생성합니다.")
    public void createCourseTest() {
        // Given
        Professor professor = professorService.findById(testProfessor.getId()).orElse(null);
        assert professor != null;
        Course course = Course.create("테스트 강좌", professor.getId(), "테스트 학과", CourseType.MAJOR_REQUIRED, 3, 30);

        // When
        Course createdCourse = professorService.createCourse(course);

        // Then
        Assertions.assertThat(createdCourse.getId()).isNotNull();
        Assertions.assertThat(createdCourse.getName()).isEqualTo("테스트 강좌");
        Assertions.assertThat(createdCourse.getProfessorId()).isEqualTo(professor.getId());
        Assertions.assertThat(createdCourse.getMajorName()).isEqualTo("테스트 학과");
    }

    @Test
    @DisplayName("교수가 강좌를 제거합니다.")
    public void removeCourseTest() {
        // Given
        Professor professor = professorService.findById(testProfessor.getId()).orElse(null);
        assert professor != null;
        Course course = Course.create("테스트 강좌", professor.getId(), "테스트 학과", CourseType.MAJOR_REQUIRED, 3, 30);
        Course createdCourse = professorService.createCourse(course);

        // When
        professorService.removeCourse(createdCourse.getId());

        // Then
        Optional<Course> foundCourse = courseRepository.findById(createdCourse.getId());
        Assertions.assertThat(foundCourse.isPresent()).isFalse();
    }

    @Test
    @DisplayName("교수가 전공을 추가합니다.")
    public void addMajorTest() {
        // Given
        Major major = new Major();
        major.setName("추가된 테스트 전공");
        Professor professor = professorService.findById(testProfessor.getId()).orElse(null);
        assert professor != null;

        // When
        professorService.addMajor(professor.getId(), major);

        // Then
        Professor updated = professorService.findById(professor.getId()).orElse(null);
        assert updated != null;
        List<String> names = updated.getMajors().stream().map(Major::getName).collect(Collectors.toList());
        Assertions.assertThat(names).contains("추가된 테스트 전공");
    }

    @Test
    @DisplayName("강좌에 학생을 등록합니다.")
    public void addStudentToCourse() {
        // Given
        Professor professor = professorService.findById(testProfessor.getId()).orElse(null);
        assert professor != null;
        Course course = Course.create("테스트 강좌", professor.getId(), "테스트 학과", CourseType.MAJOR_REQUIRED, 3, 30);
        Course createdCourse = professorService.createCourse(course);

        Major major = new Major();
        major.setName("테스트 전공");
        Student student = Student.createWithMajor("테스트 학생", major);

        // When
        professorService.addStudentToCourse(createdCourse.getId(), student);

        // Then
        Course updated = courseRepository.findById(createdCourse.getId()).orElse(null);
        assert updated != null;
        List<String> names = updated.getStudents().stream().map(Student::getName).collect(Collectors.toList());
        Assertions.assertThat(names).contains("테스트 학생");
    }

    @Test
    @DisplayName("강좌에서 학생을 제거합니다.")
    public void removeStudentFromCourse() {
        // Given
        Professor professor = professorService.findById(testProfessor.getId()).orElse(null);
        assert professor != null;
        Course course = Course.create("테스트 강좌", professor.getId(), "테스트 학과", CourseType.MAJOR_REQUIRED, 3, 30);
        Course createdCourse = professorService.createCourse(course);

        Major major = new Major();
        major.setName("테스트 전공");
        Student student = Student.createWithMajor("테스트 학생", major);
        professorService.addStudentToCourse(createdCourse.getId(), student);

        // When
        professorService.removeStudentFromCourse(createdCourse.getId(), student);

        // Then
        Course updated = courseRepository.findById(createdCourse.getId()).orElse(null);
        assert updated != null;
        List<String> names = updated.getStudents().stream().map(Student::getName).collect(Collectors.toList());
        Assertions.assertThat(names).doesNotContain("테스트 학생");
    }

}