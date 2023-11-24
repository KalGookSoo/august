package com.kalgookso.august.repository;

import com.kalgookso.august.entity.Category;
import org.springframework.data.repository.Repository;

public interface CategoryCommandRepository extends Repository<Category, String> {

    /**
     * 주어진 Category 엔티티를 저장합니다.
     *
     * @param category 저장할 Category 엔티티.
     * @return 저장된 Category 엔티티.
     */
    Category save(Category category);

    /**
     * 주어진 ID에 해당하는 Category 엔티티를 삭제합니다.
     *
     * @param id 삭제할 Category 엔티티의 ID.
     */
    void deleteById(String id);

}