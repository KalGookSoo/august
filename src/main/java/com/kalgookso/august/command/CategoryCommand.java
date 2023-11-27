package com.kalgookso.august.command;

import javax.validation.constraints.NotEmpty;

public class CategoryCommand {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}