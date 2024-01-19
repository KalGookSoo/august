package com.kalgookso.august.cms.service;

import com.kalgookso.august.cms.command.AccountUpdateCommand;
import com.kalgookso.august.cms.query.AccountCriteria;
import com.kalgookso.august.cms.entity.Account;
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