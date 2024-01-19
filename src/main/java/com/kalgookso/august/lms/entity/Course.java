package com.kalgookso.august.lms.entity;

import com.kalgookso.august.cms.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 강좌
 * 강좌는 1 .. N 개의 학생을 가질 수 있습니다.
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "tb_course")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class Course extends BaseEntity {
    private String name;
    private String professorId;
    private String majorName;
    private CourseType type;
    private int credit;
    private int capacity;

    @ManyToMany
    @JoinTable(
            name = "tb_student_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();

    /**
     * 새로운 강좌를 생성하는 메서드입니다.
     * @return 생성된 강좌 객체를 반환합니다.
     */
    public static Course create(String name, String professorId, String majorName, CourseType type, int credit, int capacity) {
        Course course = new Course();
        course.name = name;
        course.professorId = professorId;
        course.majorName = majorName;
        course.type = type;
        course.credit = credit;
        course.capacity = capacity;
        return course;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}