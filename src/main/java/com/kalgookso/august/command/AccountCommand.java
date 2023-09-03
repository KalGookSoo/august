package com.kalgookso.august.command;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class AccountCommand {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String name;
}
