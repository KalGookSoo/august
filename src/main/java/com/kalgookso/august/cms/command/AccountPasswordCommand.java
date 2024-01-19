package com.kalgookso.august.cms.command;

import com.kalgookso.august.cms.annotation.PasswordsNotEqual;

import javax.validation.constraints.NotBlank;

/**
 * 계정 패스워드 업데이트 명령에 대한 클래스입니다.
 */
@PasswordsNotEqual
public class AccountPasswordCommand {

    @NotBlank
    private final String originPassword;  // 원래 패스워드 필드

    @NotBlank
    private final String newPassword;  // 새 패스워드 필드

    public AccountPasswordCommand(@NotBlank String originPassword, @NotBlank String newPassword) {
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