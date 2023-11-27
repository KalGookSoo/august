package com.kalgookso.august.command;

import com.kalgookso.august.annotation.PasswordsNotEqual;

import javax.validation.constraints.NotBlank;

/**
 * 계정 패스워드 업데이트 명령에 대한 클래스입니다.
 * 이 클래스는 원래 패스워드와 새 패스워드를 필드로 가지고 있습니다.
 * 이 필드들은 NotBlank 제약 조건이 적용되어 있습니다.
 */
@PasswordsNotEqual
public class UpdateAccountPasswordCommand {

    @NotBlank
    private final String originPassword;  // 원래 패스워드 필드

    @NotBlank
    private final String newPassword;  // 새 패스워드 필드

    /**
     * UpdateAccountPasswordCommand 생성자입니다.
     * @param originPassword 원래 패스워드
     * @param newPassword 새 패스워드
     */
    public UpdateAccountPasswordCommand(@NotBlank String originPassword, @NotBlank String newPassword) {
        this.originPassword = originPassword;
        this.newPassword = newPassword;
    }

    /**
     * 원래 패스워드를 반환하는 메서드입니다.
     * @return 원래 패스워드
     */
    public @NotBlank String getOriginPassword() {
        return this.originPassword;
    }

    /**
     * 새 패스워드를 반환하는 메서드입니다.
     * @return 새 패스워드
     */
    public @NotBlank String getNewPassword() {
        return this.newPassword;
    }

}