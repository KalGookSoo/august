package com.kalgookso.august.command;

import javax.validation.constraints.NotBlank;

/**
 * 계정 업데이트 명령에 대한 클래스입니다.
 * 이 클래스는 이름을 필드로 가지고 있습니다.
 * 이 필드는 NotBlank 제약 조건이 적용되어 있습니다.
 */
public class UpdateAccountCommand {

    @NotBlank
    private final String name;  // 이름 필드

    /**
     * UpdateAccountCommand 생성자입니다.
     * @param name 이름
     */
    public UpdateAccountCommand(@NotBlank String name) {
        this.name = name;
    }

    /**
     * 이름을 반환하는 메서드입니다.
     * @return 이름
     */
    public @NotBlank String getName() {
        return this.name;
    }
}