package com.kalgookso.august.cms.command;

import com.kalgookso.august.cms.value.CategoryType;

import javax.validation.constraints.NotEmpty;

public class CategoryCommand {

    @NotEmpty
    private final String name;

    @NotEmpty
    private final CategoryType type;

    public CategoryCommand(String name, CategoryType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public CategoryType getType() {
        return type;
    }

}