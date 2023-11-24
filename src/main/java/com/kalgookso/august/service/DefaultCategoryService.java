package com.kalgookso.august.service;

import com.kalgookso.august.entity.Category;
import com.kalgookso.august.repository.CategoryRepository;
import com.kalgookso.august.specification.AugustSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 기본 카테고리 서비스 클래스입니다.
 * 이 클래스는 CategoryService 인터페이스를 구현하며, CategoryCommandRepository와 CategoryQueryRepository를 사용하여 카테고리 관련 작업을 수행합니다.
 */
@Service
@Transactional
public class DefaultCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * DefaultCategoryService 생성자입니다.
     *
     * @param categoryRepository 카테고리 저장소
     */
    public DefaultCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * 카테고리를 저장하는 메서드입니다.
     * @param category 저장할 카테고리
     * @return 저장된 카테고리
     */
    @Override
    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }

    /**
     * ID로 카테고리를 찾는 메서드입니다.
     * @param id 카테고리 ID
     * @return 찾은 카테고리 (Optional)
     */
    @Override
    public Optional<Category> findById(String id) {
        return this.categoryRepository.findOne(AugustSpecification.idEquals(id));
    }

    /**
     * 모든 카테고리를 페이지로 반환하는 메서드입니다.
     * @param pageable 페이지 정보
     * @return 카테고리 페이지
     */
    @Override
    public Page<Category> findAll(Pageable pageable) {
        return this.categoryRepository.findAll(Specification.where(null), pageable);
    }

    /**
     * ID로 카테고리를 삭제하는 메서드입니다.
     * @param id 삭제할 카테고리의 ID
     */
    @Override
    public void deleteById(String id) {
        this.categoryRepository.deleteById(id);
    }

}