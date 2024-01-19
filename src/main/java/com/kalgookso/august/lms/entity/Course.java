package com.kalgookso.august.lms.entity;

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
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Long professorId;

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
    public static Course create(String name, Long professorId, String majorName, CourseType type, int credit, int capacity) {
        Course course = new Course();
        course.name = name;
        course.professorId = professorId;
        course.majorName = majorName;
        course.type = type;
        course.credit = credit;
        course.capacity = capacity;
        return course;
    }

    /**
     * 강좌의 수용 인원이 주어진 수강신청 수보다 작거나 같은지를 확인하는 메서드입니다.
     * @param enrollmentCount 강좌에 현재 등록된 수강신청 수
     * @return 강좌의 수용 인원이 주어진 수강신청 수보다 작거나 같으면 true, 그렇지 않으면 false를 반환합니다.
     */
    public boolean verifyCapacity(long enrollmentCount) {
        return this.capacity <= enrollmentCount;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
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