package com.kalgookso.august.cms.service;

import com.kalgookso.august.cms.command.AccountUpdateCommand;
import com.kalgookso.august.cms.query.AccountCriteria;
import com.kalgookso.august.cms.entity.Account;
import com.kalgookso.august.cms.entity.Authority;
import com.kalgookso.august.cms.exception.UsernameAlreadyExistsException;
import com.kalgookso.august.cms.repository.AccountRepository;
import com.kalgookso.august.cms.query.AccountSpecification;
import com.kalgookso.august.cms.query.AugustSpecification;
import com.kalgookso.august.cms.value.ContactNumber;
import com.kalgookso.august.cms.value.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.kalgookso.august.cms.query.AccountSpecification.*;

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
        if (accountRepository.exists(usernameEquals(account.getUsername()))) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        account.initializeAccountPolicy();
        account.changePassword(passwordEncoder.encode(account.getPassword()));
        if (account.getAuthorities().isEmpty()) {
            account.getAuthorities().add(new Authority("ROLE_USER"));
        }
        return accountRepository.save(account);
    }

    @Override
    public Account update(String id, AccountUpdateCommand command) {
        Optional<Account> foundAccount = accountRepository.findOne(AugustSpecification.idEquals(id));
        if (foundAccount.isEmpty()) {
            throw new NoSuchElementException("계정을 찾을 수 없습니다.");
        }
        Account account = foundAccount.get();
        String name = command.getName();
        Email email = new Email(command.getEmailId(), command.getEmailDomain());
        ContactNumber contactNumber = new ContactNumber(command.getFirstContactNumber(), command.getMiddleContactNumber(), command.getLastContactNumber());
        account.setName(name);
        account.setEmail(email);
        account.setContactNumber(contactNumber);
        return account;
    }

    @Override
    public Optional<Account> findById(String id) {
        return accountRepository.findOne(AugustSpecification.idEquals(id));
    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public Page<Account> findAll(AccountCriteria criteria, Pageable pageable) {
        Specification<Account> specification = Specification.where(null);
        if (criteria.getUsername() != null) {
            specification = specification.and(usernameContains(criteria.getUsername()));
        }
        if (criteria.getName() != null) {
            specification = specification.and(nameContains(criteria.getName()));
        }
        if (criteria.getEmailId() != null) {
            specification = specification.and(emailIdContains(criteria.getEmailId()));
        }
        if (criteria.getContactNumber() != null) {
            specification = specification.and(AccountSpecification.contactNumberContains(criteria.getContactNumber()));
        }
        return accountRepository.findAll(specification, pageable);
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