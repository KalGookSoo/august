package com.kalgookso.august.command;

import javax.validation.constraints.NotBlank;

public class AccountCommand {

    public static class Post {

        @NotBlank
        private String username;

        @NotBlank
        private String password;

        @NotBlank
        private String name;

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
        private String name;

        public Put(@NotBlank String name) {
            this.name = name;
        }

        public @NotBlank String getName() {
            return this.name;
        }
    }

    public static class Password {

        @NotBlank
        private String originPassword;

        @NotBlank
        private String newPassword;

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