package com.kalgookso.august.service;

import com.kalgookso.august.command.UpdateAccountCommand;
import com.kalgookso.august.entity.account.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AccountService {

    Account create(Account account);

    Account update(String id, UpdateAccountCommand command);

    Optional<Account> findById(String id);

    Page<Account> findAll(Pageable pageable);

    void deleteById(String id);

    Account updatePassword(String id, String password);

}