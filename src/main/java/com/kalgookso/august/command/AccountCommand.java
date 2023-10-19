package com.kalgookso.august.command;

import javax.validation.constraints.NotBlank;

public class AccountCommand {

    public static class Post {

        @NotBlank
        private final String username;

        @NotBlank
        private final String password;

        @NotBlank
        private final String name;

        public Post(@NotBlank String username, @NotBlank String password, @NotBlank String name) {
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

    public static class Put {

        @NotBlank
        private final String name;

        public Put(@NotBlank String name) {
            this.name = name;
        }

        public @NotBlank String getName() {
            return this.name;
        }
    }

    public static class Password {

        @NotBlank
        private final String originPassword;

        @NotBlank
        private final String newPassword;

        public Password(@NotBlank String originPassword, @NotBlank String newPassword) {
            this.originPassword = originPassword;
            this.newPassword = newPassword;
        }

        public @NotBlank String getOriginPassword() {
            return this.originPassword;
        }

        public @NotBlank String getNewPassword() {
            return this.newPassword;
        }
    }

}