package com.kalgookso.august.command;

import javax.validation.constraints.NotBlank;

public class UpdateAccountCommand {

    @NotBlank
    private final String name;

    public UpdateAccountCommand(@NotBlank String name) {
        this.name = name;
    }

    public @NotBlank String getName() {
        return this.name;
    }
}