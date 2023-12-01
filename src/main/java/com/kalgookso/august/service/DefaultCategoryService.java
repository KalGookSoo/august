package com.kalgookso.august.service;

import com.kalgookso.august.command.CategoryCommand;
import com.kalgookso.august.entity.Category;
import com.kalgookso.august.event.AclObjectCreatedEvent;
import com.kalgookso.august.event.AclObjectDeletedEvent;
import com.kalgookso.august.mapper.CategoryMapper;
import com.kalgookso.august.repository.CategoryRepository;
import com.kalgookso.august.specification.AugustSpecification;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class DefaultCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ApplicationEventPublisher eventPublisher;

    public DefaultCategoryService(CategoryRepository categoryRepository, ApplicationEventPublisher eventPublisher) {
        this.categoryRepository = categoryRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Category create(Category category) {
        Category createdCategory = categoryRepository.save(category);
        eventPublisher.publishEvent(new AclObjectCreatedEvent(Category.class, createdCategory.getId(), createdCategory.getCreatedBy()));
        return createdCategory;
    }

    @Override
    public Category update(String id, CategoryCommand command) {
        final Optional<Category> foundCategory = findById(id);
        if (foundCategory.isEmpty()) {
            throw new NoSuchElementException("카테고리를 찾을 수 없습니다.");
        }
        final Category category = foundCategory.get();
        return CategoryMapper.INSTANCE.updateEntityFromCommand(command, category);
    }

    @Override
    public Optional<Category> findById(String id) {
        return categoryRepository.findOne(AugustSpecification.idEquals(id));
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(Specification.where(null), pageable);
    }

    @Override
    public void deleteById(String id) {
        categoryRepository.deleteById(id);
        eventPublisher.publishEvent(new AclObjectDeletedEvent(Category.class, id));
    }

}