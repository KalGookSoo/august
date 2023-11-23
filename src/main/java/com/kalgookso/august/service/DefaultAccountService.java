package com.kalgookso.august.service;

import com.kalgookso.august.entity.Account;
 import com.kalgookso.august.repository.AccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

/**
 * 기본 계정 서비스 클래스입니다.
 * 이 클래스는 AccountService 인터페이스를 구현하며, AccountRepository를 사용하여 계정 관련 작업을 수행합니다.
 */
@Service
public class DefaultAccountService implements AccountService {

    private final AccountRepository accountRepository;  // 계정 저장소

    /**
     * DefaultAccountService 생성자입니다.
     * @param accountRepository 계정 저장소
     */
    public DefaultAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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
        return this.accountRepository.findByUsername(username);
    }

    /**
     * ID로 계정을 찾는 메서드입니다.
     * @param id 계정 ID
     * @return 찾은 계정 (Optional)
     */
    @Override
    public Optional<Account> findById(String id) {
        return this.accountRepository.findById(id);
    }

    /**
     * 모든 계정을 페이지로 반환하는 메서드입니다.
     * 현재는 빈 페이지를 반환합니다.
     * @param pageable 페이지 정보
     * @return 계정 페이지
     */
    @Override
    public Page<Account> findAll(Pageable pageable) {
        return new PageImpl<>(Collections.emptyList(), pageable, 0L);
    }

    /**
     * ID로 계정을 삭제하는 메서드입니다.
     * @param id 삭제할 계정의 ID
     */
    @Override
    public void deleteById(String id) {
        this.accountRepository.deleteById(id);
    }

}