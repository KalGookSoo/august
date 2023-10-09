package com.kalgookso.august.command;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class AccountCommand {

    @Setter
    @Getter
    public static class Post {

        @NotBlank
        private String username;

        @NotBlank
        private String password;

        @NotBlank
        private String name;

    }

    @Setter
    @Getter
    public static class Put {

        @NotBlank
        private String name;

    }

    @Setter
    @Getter
    public static class Response {

        private String id;

        private String username;

        private String name;

    }

}
