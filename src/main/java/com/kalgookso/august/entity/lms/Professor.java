package com.kalgookso.august.entity.lms;

import com.kalgookso.august.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "tb_professor")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class Professor extends BaseEntity {
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tb_professor_major",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "major_id")
    )
    private List<Major> majors = new ArrayList<>();

    /**
     * 교수를 등록하고 첫 번째 전공을 추가하는 팩토리 메서드입니다.
     *
     * @param name 등록하려는 교수의 이름입니다.
     * @param major 교수의 첫 번째 전공입니다.
     * @return 등록된 교수 객체를 반환합니다. 이 교수의 이름은 입력받은 이름으로 설정되고, majors 리스트에는 입력받은 전공이 추가됩니다.
     */
    public static Professor createWithMajor(String name, Major major) {
        Professor professor = new Professor();
        professor.name = name;
        professor.majors.add(major);
        return professor;
    }

    /**
     * 교수가 새로운 강좌를 생성하는 메서드입니다.
     *
     * @param name 생성하려는 강좌의 이름입니다.
     * @return 생성된 강좌 객체를 반환합니다. 이 강좌의 이름은 입력받은 이름으로 설정되고, 교수의 식별자는 현재 교수의 식별자로 설정됩니다.
     */
    public Course createCourse(String name, String majorName) {
        Course course = new Course();
        course.setName(name);
        course.setProfessorId(this.getId());
        course.setMajorName(majorName);
        return course;
    }

    /**
     * 교수가 전공을 추가하는 메서드입니다.
     *
     * @param major 교수가 추가하려는 전공입니다.
     * @throws IllegalArgumentException 이미 추가된 전공을 다시 추가하려고 할 때 발생합니다.
     */
    public void addMajor(Major major) {
        if (this.majors.contains(major)) {
            throw new IllegalArgumentException("You have already added this major.");
        }
        this.majors.add(major);
    }
}