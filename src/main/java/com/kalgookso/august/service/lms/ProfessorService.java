package com.kalgookso.august.service.lms;

import com.kalgookso.august.entity.lms.*;

import java.util.List;
import java.util.Optional;

public interface ProfessorService {

    /**
     * 교수를 생성합니다.
     * @param professor 생성할 교수입니다.
     * @return 생성된 교수 객체를 반환합니다.
     */
    Professor create(Professor professor);

    /**
     * 교수 식별자로 교수를 조회합니다.
     * @param id 조회할 교수의 식별자입니다.
     */
    Optional<Professor> findById(String id);

    /**
     * 교수명으로 교수를 조회합니다.
     * @param name 조회할 교수의 이름입니다.
     * @return 조회된 교수 목록을 반환합니다.
     */
    List<Professor> findAllByName(String name);

    /**
     * 교수를 제거합니다.
     * @param id 제거할 교수의 식별자입니다.
     */
    void remove(String id);

    Course createCourse(Course course);

    /**
     * 교수가 강좌를 제거합니다.
     * @param courseId 제거할 강좌의 식별자입니다.
     */
    void removeCourse(String courseId);

    /**
     * 교수가 전공을 추가합니다.
     * @param id 전공을 추가할 교수의 식별자입니다.
     * @param major 추가할 전공입니다.
     */
    void addMajor(String id, Major major);

    /**
     * 교수가 강좌에 학생을 등록합니다.
     * @param courseId 학생을 등록할 강좌의 식별자입니다.
     * @param student 등록할 학생입니다.
     */
    void addStudentToCourse(String courseId, Student student);

    /**
     * 교수가 강좌에서 학생을 제거합니다.
     * @param courseId 학생을 제거할 강좌의 식별자입니다.
     * @param student 제거할 학생입니다.
     */
    void removeStudentFromCourse(String courseId, Student student);
}