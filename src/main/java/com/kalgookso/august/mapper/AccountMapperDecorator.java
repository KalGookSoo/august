package com.kalgookso.august.mapper;

import com.kalgookso.august.command.CreateAccountCommand;
import com.kalgookso.august.command.UpdateAccountCommand;
import com.kalgookso.august.entity.Account;

public class AccountMapperDecorator implements AccountMapper {

    private final AccountMapper accountMapper;

    public AccountMapperDecorator(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }


    @Override
    public Account toEntity(CreateAccountCommand command) {
        return null;
    }

    @Override
    public Account toEntity(Account originEntity, UpdateAccountCommand command) {
        return null;
    }

}