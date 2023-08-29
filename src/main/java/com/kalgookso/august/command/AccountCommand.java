package com.kalgookso.august.command;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class AccountCommand {

    @NotNull
    private String username;

    @NotNull
    private String password;

    private String name;
}
