package com.kalgookso.august.cms.mapper;

import com.kalgookso.august.cms.command.CategoryCommand;
import com.kalgookso.august.cms.entity.Category;
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