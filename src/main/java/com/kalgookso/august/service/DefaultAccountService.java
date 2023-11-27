package com.kalgookso.august.service;

import com.kalgookso.august.entity.Account;
import com.kalgookso.august.entity.Authority;
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
 * DefaultAccountService 클래스는 AccountService 인터페이스를 구현합니다.
 * 이 클래스는 계정 관련 작업을 수행하는 서비스 클래스입니다.
 */
@Service
@Transactional
public class DefaultAccountService implements AccountService {

    /**
     * AccountRepository 인스턴스입니다.
     */
    private final AccountRepository accountRepository;

    /**
     * 비밀번호 인코더입니다.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * DefaultAccountService 생성자입니다.
     *
     * @param accountRepository 계정 저장소
     * @param passwordEncoder   비밀번호 인코더
     */
    public DefaultAccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 계정을 저장하고 저장된 계정을 반환하는 메서드입니다.
     * @param account 저장할 계정
     * @return 저장된 계정
     */
    @Override
    public Account create(Account account) {
        account.setPassword(this.passwordEncoder.encode(account.getPassword()));
        if (account.getAuthorities().isEmpty()) {
            account.getAuthorities().add(new Authority("ROLE_USER"));
        }
        return this.accountRepository.save(account);
    }

    /**
     * 계정을 수정하고 수정된 계정을 반환하는 메서드입니다.
     * @param account 수정할 계정
     * @return 수정된 계정
     */
    @Override
    public Account save(Account account) {
        return this.accountRepository.save(account);
    }

    /**
     * 사용자 이름으로 계정을 찾고 찾은 계정을 반환하는 메서드입니다.
     * @param username 사용자 이름
     * @return 찾은 계정 (Optional)
     */
    @Override
    public Optional<Account> findByUsername(String username) {
        return this.accountRepository.findOne(AugustSpecification.usernameEquals(username));
    }

    /**
     * ID로 계정을 찾고 찾은 계정을 반환하는 메서드입니다.
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
     * @param account 계정
     * @return 변경된 계정
     */
    @Override
    public Account updatePassword(Account account) {
        account.setPassword(this.passwordEncoder.encode(account.getPassword()));
        return this.accountRepository.save(account);
    }

}