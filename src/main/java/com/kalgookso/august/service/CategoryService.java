package com.kalgookso.august.service;

import com.kalgookso.august.command.CategoryCommand;
import com.kalgookso.august.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {

    Category create(Category category);

    Category update(String id, CategoryCommand command);

    Optional<Category> findById(String id);

    Page<Category> findAll(Pageable pageable);

    void deleteById(String id);

}