package com.kalgookso.august.entity.lms;

import com.kalgookso.august.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "tb_course")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class Course extends BaseEntity {
    private String name;
    private String professorId;
    private String majorName;

    @ManyToMany(mappedBy = "courses")
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
}