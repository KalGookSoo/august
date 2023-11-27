package com.kalgookso.august.event;

import com.kalgookso.august.entity.Category;
import org.springframework.context.ApplicationEvent;

public class CategoryDeletedEvent extends ApplicationEvent {

    private final Category category;

    public CategoryDeletedEvent(Category category) {
        super(category);
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

}