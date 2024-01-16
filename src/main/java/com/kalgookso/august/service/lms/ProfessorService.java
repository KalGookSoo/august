package com.kalgookso.august.service.lms;

import com.kalgookso.august.entity.lms.Course;
 import com.kalgookso.august.entity.lms.Major;
import com.kalgookso.august.entity.lms.Professor;
import com.kalgookso.august.entity.lms.Student;

import java.util.List;
import java.util.Optional;

public interface ProfessorService {
    Professor create(Professor professor);
    Optional<Professor> findById(String id);
    List<Professor> findAllByName(String name);
    void remove(String id);
    Course createCourse(String professorId, String courseName, String majorName);
    void removeCourse(String courseId);
    void addMajor(String id, Major major);
    void addStudentToCourse(String courseId, Student student);
    void removeStudentFromCourse(String courseId, Student student);
}