package com.kalgookso.august.service;

import com.kalgookso.august.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 계정 서비스
 */
@Service
public class DefaultAccountService implements AccountService {

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

}