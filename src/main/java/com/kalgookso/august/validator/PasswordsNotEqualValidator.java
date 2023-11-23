package com.kalgookso.august.validator;

import com.kalgookso.august.annotation.PasswordsNotEqual;
import com.kalgookso.august.command.UpdateAccountPasswordCommand;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 비밀번호가 같지 않아야 하는 제약 조건을 검증하는 클래스입니다.
 * 이 클래스는 ConstraintValidator 인터페이스를 구현하며, PasswordsNotEqual 애노테이션과 UpdateAccountPasswordCommand 클래스를 사용합니다.
 */
public class PasswordsNotEqualValidator implements ConstraintValidator<PasswordsNotEqual, UpdateAccountPasswordCommand> {

    /**
     * 제약 조건 초기화 메서드입니다.
     * 현재는 아무런 동작을 하지 않습니다.
     * @param constraintAnnotation 제약 조건 애노테이션
     */
    @Override
    public void initialize(PasswordsNotEqual constraintAnnotation) {
    }

    /**
     * 제약 조건이 유효한지 검증하는 메서드입니다.
     * 원래 비밀번호와 새 비밀번호가 같지 않은 경우에만 true를 반환합니다.
     * @param value 검증할 객체
     * @param context 제약 조건 컨텍스트
     * @return 제약 조건이 유효한 경우 true, 그렇지 않은 경우 false
     */
    @Override
    public boolean isValid(UpdateAccountPasswordCommand value, ConstraintValidatorContext context) {
        return !value.getOriginPassword().equals(value.getNewPassword());
    }

}