package com.kalgookso.august.service;

import com.kalgookso.august.command.AccountCommand;
import com.kalgookso.august.entity.Account;
import com.kalgookso.august.entity.Authority;
import com.kalgookso.august.mapper.AccountMapper;
import com.kalgookso.august.repository.AccountRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 계정 서비스
 */
@Service
public class DefaultAccountService implements AccountService {

    @Override
    public Account create(AccountCommand.Post command) {
        return null;
    }

    @Override
    public Account save(Account account) {
        return null;
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<Account> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public boolean isMatch(CharSequence rawPassword, String encodedPassword) {
        return false;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return null;
    }

}