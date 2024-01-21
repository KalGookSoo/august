package com.kalgookso.august.cms.service;

import com.kalgookso.august.cms.command.CategoryCommand;
import com.kalgookso.august.cms.entity.Category;
import com.kalgookso.august.cms.repository.CategoryRepository;
import com.kalgookso.august.cms.value.CategoryType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class CategoryServiceTest {

    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    private Category testCategory;

    @BeforeEach
    void setup() {
        categoryService = new DefaultCategoryService(categoryRepository);
        Category category = Category.create("테스트 카테고리", CategoryType.PUBLIC);
        testCategory = categoryService.create(category);
    }

    @Test
    @DisplayName("공개 카테고리를 생성합니다.")
    void createCategoryTest() {
        Category category = Category.create("테스트 카테고리", CategoryType.PUBLIC);
        Category createdCategory = categoryService.create(category);
        assertNotNull(createdCategory.getId());
        assertThat(createdCategory.getName()).isEqualTo("테스트 카테고리");
        assertThat(createdCategory.getType()).isEqualTo(CategoryType.PUBLIC);
    }

    @Test
    @DisplayName("카테고리를 수정합니다.")
    void updateCategoryTest() {
        CategoryCommand command = new CategoryCommand("수정된 카테고리", CategoryType.PRIVATE);
        Category category = categoryService.update(testCategory.getId(), command);
        assertThat(category.getName()).isEqualTo("수정된 카테고리");
        assertThat(category.getType()).isEqualTo(CategoryType.PRIVATE);
    }

    @Test
    @DisplayName("카테고리를 삭제합니다.")
    void deleteCategoryTest() {
        categoryService.deleteById(testCategory.getId());
        Optional<Category> foundCategory = categoryService.findById(testCategory.getId());
        assertThat(foundCategory.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("카테고리를 조회합니다.")
    void findById() {
        Optional<Category> foundCategory = categoryService.findById(testCategory.getId());
        assertThat(foundCategory.isPresent()).isTrue();
        assertThat(foundCategory.get().getName()).isEqualTo("테스트 카테고리");
        assertThat(foundCategory.get().getType()).isEqualTo(CategoryType.PUBLIC);
    }

    @Test
    @DisplayName("카테고리 목록을 조회합니다.")
    void findAll() {
        Category category = Category.create("테스트 카테고리2", CategoryType.PUBLIC);
        categoryService.create(category);
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name").descending());
        Page<Category> page = categoryService.findAll(pageable);
        assertThat(page.getTotalElements()).isEqualTo(2);
        assertThat(page.getContent().get(0).getName()).isEqualTo("테스트 카테고리2");
        assertThat(page.getContent().get(1).getName()).isEqualTo("테스트 카테고리");
    }
}