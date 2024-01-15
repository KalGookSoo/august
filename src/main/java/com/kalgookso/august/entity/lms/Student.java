package com.kalgookso.august.entity.lms;

import com.kalgookso.august.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    public void addEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
    }

    public void addCourse(Course course) {
        Optional<Enrollment> foundEnrollment = enrollments.stream()
                .filter(enrollment -> enrollment.getCourseId().equals(course.getId()))
                .findFirst();
        if (foundEnrollment.isPresent()) {
            Enrollment enrollment = foundEnrollment.get();
            enrollment.changeStatus(EnrollmentStatus.COMPLETED);
            // TODO enrollment의 연관관계를 학생과 끊어야할 것 같다. 왜냐하면 학생이 강좌를 등록하면 더이상 enrollment는 필요가 없기 때문이다.
        }
        this.courses.add(course);
    }
}