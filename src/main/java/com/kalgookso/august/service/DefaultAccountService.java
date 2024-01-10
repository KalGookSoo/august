package com.kalgookso.august.service;

import com.kalgookso.august.command.UpdateAccountCommand;
import com.kalgookso.august.entity.account.Account;
import com.kalgookso.august.entity.account.Authority;
import com.kalgookso.august.exception.UsernameAlreadyExistsException;
import com.kalgookso.august.mapper.AccountMapper;
import com.kalgookso.august.repository.AccountRepository;
import com.kalgookso.august.specification.AugustSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class DefaultAccountService implements AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    public DefaultAccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Account create(Account account) {
        if (accountRepository.exists(AugustSpecification.usernameEquals(account.getUsername()))) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        account.changePassword(passwordEncoder.encode(account.getPassword()));
        if (account.getAuthorities().isEmpty()) {
            account.getAuthorities().add(new Authority("ROLE_USER"));
        }
        return accountRepository.save(account);
    }

    @Override
    public Account update(String id, UpdateAccountCommand command) {
        Optional<Account> foundAccount = accountRepository.findOne(AugustSpecification.idEquals(id));
        if (foundAccount.isEmpty()) {
            throw new NoSuchElementException("계정을 찾을 수 없습니다.");
        }
        return AccountMapper.INSTANCE.toEntity(foundAccount.get(), command);
    }

    @Override
    public Optional<Account> findById(String id) {
        return accountRepository.findOne(AugustSpecification.idEquals(id));
    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(Specification.where(null), pageable);
    }

    @Override
    public void deleteById(String id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void updatePassword(String id, String password) {
        Optional<Account> foundAccount = accountRepository.findOne(AugustSpecification.idEquals(id));
        if (foundAccount.isPresent()) {
            Account account = foundAccount.get();
            account.changePassword(passwordEncoder.encode(password));
        } else {
            throw new NoSuchElementException("Account not found");
        }
    }

}