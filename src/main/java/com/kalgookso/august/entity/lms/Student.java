package com.kalgookso.august.entity.lms;

import com.kalgookso.august.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 수강생
 * 수강생은 0 .. N 개의 수강신청을 할 수 있습니다.
 * 수강생은 1 .. N 개의 강좌를 수강할 수 있습니다.
 * 수강생은 1 .. N 개의 전공을 가질 수 있습니다.
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "tb_student")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class Student extends BaseEntity {
    private String name;

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

    public String getName() {
        return name;
    }
}