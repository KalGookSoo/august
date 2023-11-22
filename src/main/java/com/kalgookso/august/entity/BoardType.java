package com.kalgookso.august.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "tb_board_type")
public class BoardType {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private String name;

    @CreatedBy
    private String createdBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

}