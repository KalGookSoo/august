package com.kalgookso.august.service;

import com.kalgookso.august.entity.Account;
import com.kalgookso.august.exception.UsernameAlreadyExistsException;
import com.kalgookso.august.repository.AccountRepository;
import com.kalgookso.august.specification.AugustSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 기본 계정 서비스 클래스입니다.
 * 이 클래스는 AccountService 인터페이스를 구현하며, AccountCommandRepository와 AccountQueryRepository를 사용하여 계정 관련 작업을 수행합니다.
 */
@Service
@Transactional
public class DefaultAccountService implements AccountService {

    private final AccountRepository accountRepository;
    
    private final PasswordEncoder passwordEncoder;

    /**
     * DefaultAccountService 생성자입니다.
     *
     * @param accountRepository 계정 저장소
     * @param passwordEncoder   패스워드 인코더
     */
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
        return accountRepository.save(account);
    }

    /**
     * 계정을 저장하는 메서드입니다.
     * @param account 저장할 계정
     * @return 저장된 계정
     */
    @Override
    public Account save(Account account) {
        return this.accountRepository.save(account);
    }

    /**
     * 사용자 이름으로 계정을 찾는 메서드입니다.
     * @param username 사용자 이름
     * @return 찾은 계정 (Optional)
     */
    @Override
    public Optional<Account> findByUsername(String username) {
        return this.accountRepository.findOne(AugustSpecification.usernameEquals(username));
    }

    /**
     * ID로 계정을 찾는 메서드입니다.
     * @param id 계정 ID
     * @return 찾은 계정 (Optional)
     */
    @Override
    public Optional<Account> findById(String id) {
        return this.accountRepository.findOne(AugustSpecification.idEquals(id));
    }

    /**
     * 모든 계정을 페이지로 반환하는 메서드입니다.
     * @param pageable 페이지 정보
     * @return 계정 페이지
     */
    @Override
    public Page<Account> findAll(Pageable pageable) {
        return this.accountRepository.findAll(Specification.where(null), pageable);
    }

    /**
     * ID로 계정을 삭제하는 메서드입니다.
     * @param id 삭제할 계정의 ID
     */
    @Override
    public void deleteById(String id) {
        this.accountRepository.deleteById(id);
    }

    /**
     * 계정의 비밀번호를 변경하는 메서드입니다.
     * @param id 계정 ID
     * @param password 변경할 비밀번호
     */
    @Override
    public void changePassword(String id, String password) {
        Optional<Account> foundAccount = this.accountRepository.findOne(AugustSpecification.idEquals(id));
        if (foundAccount.isPresent()) {
            Account account = foundAccount.get();
            account.changePassword(passwordEncoder.encode(password));
            this.accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Account not found");
        }
    }

}