package com.kalgookso.august.entity.lms;

import com.kalgookso.august.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 강좌
 * 강좌는 1 .. N 개의 수강생을 가질 수 있습니다.
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

    @ManyToMany
    @JoinTable(
            name = "tb_student_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getName() {
        return name;
    }

    public String getProfessorId() {
        return professorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }
}