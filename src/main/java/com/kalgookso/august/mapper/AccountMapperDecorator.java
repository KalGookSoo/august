package com.kalgookso.august.mapper;

import com.kalgookso.august.command.AccountUpdateCommand;
import com.kalgookso.august.command.AccountCreateCommand;
import com.kalgookso.august.entity.account.Account;

/**
 * 계정 매퍼 데코레이터 클래스입니다.
 * 이 클래스는 AccountMapper 인터페이스를 구현하며, 실제 AccountMapper를 래핑합니다.
 */
public class AccountMapperDecorator implements AccountMapper {

    private final AccountMapper accountMapper;  // 실제 계정 매퍼

    /**
     * AccountMapperDecorator 생성자입니다.
     * @param accountMapper 실제 계정 매퍼
     */
    public AccountMapperDecorator(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    /**
     * 계정 생성 명령을 계정 엔티티로 변환하는 메서드입니다.
     * 실제 계정 매퍼의 동일한 메서드를 호출하여 변환을 수행합니다.
     * @param command 계정 생성 명령
     * @return 변환된 계정 엔티티
     */
    @Override
    public Account toEntity(AccountCreateCommand command) {
        return this.accountMapper.toEntity(command);
    }

    /**
     * 계정 업데이트 명령을 기존 계정 엔티티에 적용하여 새 계정 엔티티를 생성하는 메서드입니다.
     * 실제 계정 매퍼의 동일한 메서드를 호출하여 변환을 수행합니다.
     * @param originEntity 기존 계정 엔티티
     * @param command 계정 업데이트 명령
     * @return 변환된 계정 엔티티
     */
    @Override
    public Account toEntity(Account originEntity, AccountUpdateCommand command) {
        return this.accountMapper.toEntity(originEntity, command);
    }

}