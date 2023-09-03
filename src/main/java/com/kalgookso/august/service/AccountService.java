package com.kalgookso.august.service;

import com.kalgookso.august.command.AccountCommand;
import com.kalgookso.august.entity.Account;
import com.kalgookso.august.entity.Authority;
import com.kalgookso.august.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    public Account test(String username, String password, String name) {
        Account account = new Account(username, this.passwordEncoder.encode(password), name);
        return this.accountRepository.save(account);
    }

    public Account save(AccountCommand command) {
        Account account = new Account(command.getUsername(), this.passwordEncoder.encode(command.getPassword()), command.getName());
        account.addAuthority(new Authority("ROLE_USER"));
        return this.accountRepository.save(account);
    }

    public Optional<Account> findById(UUID id) {
        return this.accountRepository.findById(id);
    }

    public Optional<Account> findByUsername(String username) {
        return this.accountRepository.findByUsername(username);
    }

}
