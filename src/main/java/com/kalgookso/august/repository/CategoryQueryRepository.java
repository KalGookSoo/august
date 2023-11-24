package com.kalgookso.august.repository;

import com.kalgookso.august.entity.Category;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

public interface CategoryQueryRepository extends Repository<Category, String>, JpaSpecificationExecutor<Category> {

}