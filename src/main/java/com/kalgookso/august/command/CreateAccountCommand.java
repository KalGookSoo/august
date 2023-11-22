package com.kalgookso.august.command;

import javax.validation.constraints.NotBlank;

public class CreateAccountCommand {

    @NotBlank
    private final String username;

    @NotBlank
    private final String password;

    @NotBlank
    private final String name;

    public CreateAccountCommand(@NotBlank String username, @NotBlank String password, @NotBlank String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public @NotBlank String getUsername() {
        return this.username;
    }

    public @NotBlank String getPassword() {
        return this.password;
    }

    public @NotBlank String getName() {
        return this.name;
    }
}