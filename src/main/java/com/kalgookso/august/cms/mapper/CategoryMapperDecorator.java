package com.kalgookso.august.cms.mapper;

import com.kalgookso.august.cms.command.CategoryCommand;
import com.kalgookso.august.cms.entity.Category;
import org.springframework.stereotype.Component;

@Component
public abstract class CategoryMapperDecorator implements CategoryMapper {

    private final CategoryMapper delegate;

    public CategoryMapperDecorator(CategoryMapper delegate) {
        this.delegate = delegate;
    }

    @Override
    public CategoryCommand toCommand(Category category) {
        CategoryCommand command = delegate.toCommand(category);
        // 추가적인 매핑 로직을 이곳에 작성합니다.
        return command;
    }

    @Override
    public Category toEntity(CategoryCommand command) {
        Category category = delegate.toEntity(command);
        // 추가적인 매핑 로직을 이곳에 작성합니다.
        return category;
    }

    @Override
    public Category updateEntityFromCommand(CategoryCommand command, Category category) {
        Category mappedCategory = delegate.updateEntityFromCommand(command, category);
        // 추가적인 매핑 로직을 이곳에 작성합니다.
        return mappedCategory;
    }
}