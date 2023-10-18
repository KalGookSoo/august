package com.kalgookso.august.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class AccountCommand {

    @Getter
    @AllArgsConstructor
    public static class Post {

        @NotBlank
        private String username;

        @NotBlank
        private String password;

        @NotBlank
        private String name;

    }

    @Getter
    @AllArgsConstructor
    public static class Put {

        @NotBlank
        private String name;

    }

    @Getter
    @AllArgsConstructor
    public static class Password {

        @NotBlank
        private String originPassword;

        @NotBlank
        private String newPassword;

    }

}