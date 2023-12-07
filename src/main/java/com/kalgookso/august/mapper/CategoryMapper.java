package com.kalgookso.august.mapper;

import com.kalgookso.august.command.CategoryCommand;
import com.kalgookso.august.entity.article.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = CategoryMapperDecorator.class)
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryCommand toCommand(Category category);

    Category toEntity(CategoryCommand command);

    Category updateEntityFromCommand(CategoryCommand command, @MappingTarget Category category);
}