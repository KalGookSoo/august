package com.kalgookso.august.command;

import javax.validation.constraints.NotBlank;

/**
 * 계정 생성 명령에 대한 클래스입니다.
 * 이 클래스는 계정명, 패스워드, 이름을 필드로 가지고 있습니다.
 * 각 필드는 NotBlank 제약 조건이 적용되어 있습니다.
 */
public class CreateAccountCommand {
    @NotBlank
    private final String username;  // 계정명 필드

    @NotBlank
    private final String password;  // 패스워드 필드

    @NotBlank
    private final String name;  // 이름 필드

    /**
     * CreateAccountCommand 생성자입니다.
     * @param username 계정명
     * @param password 패스워드
     * @param name 이름
     */
    public CreateAccountCommand(@NotBlank String username, @NotBlank String password, @NotBlank String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    /**
     * 계정명을 반환하는 메서드입니다.
     * @return 계정명
     */
    public @NotBlank String getUsername() {
        return this.username;
    }

    /**
     * 패스워드를 반환하는 메서드입니다.
     * @return 패스워드
     */
    public @NotBlank String getPassword() {
        return this.password;
    }

    /**
     * 이름을 반환하는 메서드입니다.
     * @return 이름
     */
    public @NotBlank String getName() {
        return this.name;
    }
}