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
@Table(name = "tb_major")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class Major extends BaseEntity {
    private String name;

    @ManyToMany(mappedBy = "majors")
    private List<Professor> professors = new ArrayList<>();

    @ManyToMany(mappedBy = "majors")
    private List<Student> students = new ArrayList<>();
}