package com.kalgookso.august.service.lms;

import com.kalgookso.august.entity.lms.Course;
import com.kalgookso.august.entity.lms.Professor;

public interface ProfessorService {
    Professor create(Professor professor);
    Course createCourse(String professorId, String courseName, String majorName);
}