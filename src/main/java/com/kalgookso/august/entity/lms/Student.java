package com.kalgookso.august.entity.lms;

import com.kalgookso.august.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "tb_student")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class Student extends BaseEntity {
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "tb_student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tb_student_major",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "major_id")
    )
    private List<Major> majors = new ArrayList<>();

    /**
     * 학생을 등록하고 첫 번째 전공을 추가하는 팩토리 메서드입니다.
     *
     * @param name 등록하려는 학생의 이름입니다.
     * @param major 학생의 첫 번째 전공입니다.
     * @return 등록된 학생 객체를 반환합니다. 이 학생의 이름은 입력받은 이름으로 설정되고, majors 리스트에는 입력받은 전공이 추가됩니다.
     */
    public static Student createWithMajor(String name, Major major) {
        Student student = new Student();
        student.name = name;
        student.majors.add(major);
        return student;
    }

    /**
     * 학생이 전공을 추가하는 메서드입니다.
     *
     * @param major 학생이 추가하려는 전공입니다.
     * @throws IllegalArgumentException 이미 추가된 전공을 다시 추가하려고 할 때 발생합니다.
     */
    public void addMajor(Major major) {
        if (this.majors.contains(major)) {
            throw new IllegalArgumentException("You have already added this major.");
        }
        this.majors.add(major);
    }

    /**
     * 학생이 강좌에 등록하는 메서드입니다.
     *
     * @param course 학생이 등록하려는 강좌입니다.
     * @throws IllegalArgumentException 이미 등록된 강좌에 다시 등록하려고 할 때 발생합니다.
     */
    public void enrollCourse(Course course) {
        if (this.courses.contains(course)) {
            throw new IllegalArgumentException("You have already enrolled in this course.");
        }
        this.courses.add(course);
    }

    /**
     * 학생이 강좌를 드롭하는 메서드입니다.
     *
     * @param course 학생이 드롭하려는 강좌입니다.
     * @throws IllegalArgumentException 등록되지 않은 강좌를 드롭하려고 할 때 발생합니다.
     */
    public void dropCourse(Course course) {
        if (!this.courses.contains(course)) {
            throw new IllegalArgumentException("You are not enrolled in this course.");
        }
        this.courses.remove(course);
    }

    /**
     * 학생이 강좌에 신청하는 메서드입니다.
     *
     * @param course 학생이 신청하려는 강좌입니다.
     * @throws IllegalArgumentException 이미 신청한 강좌에 다시 신청하려고 할 때 발생합니다.
     */
    public void applyForCourse(Course course) {
        for (Enrollment enrollment : this.enrollments) {
            if (enrollment.getCourseId().equals(course.getId())) {
                throw new IllegalArgumentException("You have already applied for this course.");
            }
        }
        Enrollment pendingEnrollment = Enrollment.createPendingEnrollment(course.getId());
        this.enrollments.add(pendingEnrollment);
    }

    /**
     * 학생이 강좌 신청을 취소하는 메서드입니다.
     *
     * @param course 학생이 신청 취소하려는 강좌입니다.
     * @throws IllegalArgumentException 신청하지 않은 강좌의 신청을 취소하려고 할 때 발생합니다.
     */
    public void cancelApplication(Course course) {
        Enrollment enrollmentToCancel = this.enrollments.stream()
                .filter(enrollment -> enrollment.getCourseId().equals(course.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("You have not applied for this course."));
        this.enrollments.remove(enrollmentToCancel);
    }
}