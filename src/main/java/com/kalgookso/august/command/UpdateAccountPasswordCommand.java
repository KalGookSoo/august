package com.kalgookso.august.command;

import javax.validation.constraints.NotBlank;

public class UpdateAccountPasswordCommand {

    @NotBlank
    private final String originPassword;

    @NotBlank
    private final String newPassword;

    public UpdateAccountPasswordCommand(@NotBlank String originPassword, @NotBlank String newPassword) {
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