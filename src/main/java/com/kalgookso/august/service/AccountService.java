package com.kalgookso.august.service;

import com.kalgookso.august.command.AccountUpdateCommand;
import com.kalgookso.august.criteria.AccountCriteria;
import com.kalgookso.august.entity.account.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AccountService {

    Account create(Account account);

    Account update(String id, AccountUpdateCommand command);

    Optional<Account> findById(String id);

    Page<Account> findAll(Pageable pageable);

    Page<Account> findAll(AccountCriteria criteria, Pageable pageable);

    void deleteById(String id);

    void updatePassword(String id, String password);

}