package com.kalgookso.august.cms.service;

import com.kalgookso.august.cms.command.CategoryCommand;
import com.kalgookso.august.cms.entity.Category;
import com.kalgookso.august.cms.event.AclObjectCreatedEvent;
import com.kalgookso.august.cms.event.AclObjectDeletedEvent;
import com.kalgookso.august.cms.mapper.CategoryMapper;
import com.kalgookso.august.cms.repository.CategoryRepository;
import com.kalgookso.august.cms.query.AugustSpecification;
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
        Optional<Category> foundCategory = findById(id);
        if (foundCategory.isEmpty()) {
            throw new NoSuchElementException("카테고리를 찾을 수 없습니다.");
        }
        Category category = foundCategory.get();
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