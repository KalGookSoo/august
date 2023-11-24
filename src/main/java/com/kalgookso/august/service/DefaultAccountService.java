package com.kalgookso.august.service;

import com.kalgookso.august.entity.Account;
import com.kalgookso.august.repository.AccountCommandRepository;
import com.kalgookso.august.repository.AccountQueryRepository;
import com.kalgookso.august.specification.AccountSpecification;
import com.kalgookso.august.specification.AugustSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    private final AccountCommandRepository accountCommandRepository;  // 계정 명령 저장소
    private final AccountQueryRepository accountQueryRepository;  // 계정 쿼리 저장소

    /**
     * DefaultAccountService 생성자입니다.
     * @param accountCommandRepository 계정 명령 저장소
     * @param accountQueryRepository 계정 쿼리 저장소
     */
    public DefaultAccountService(AccountCommandRepository accountCommandRepository, AccountQueryRepository accountQueryRepository) {
        this.accountCommandRepository = accountCommandRepository;
        this.accountQueryRepository = accountQueryRepository;
    }

    /**
     * 계정을 저장하는 메서드입니다.
     * @param account 저장할 계정
     * @return 저장된 계정
     */
    @Override
    public Account save(Account account) {
        return this.accountCommandRepository.save(account);
    }

    /**
     * 사용자 이름으로 계정을 찾는 메서드입니다.
     * @param username 사용자 이름
     * @return 찾은 계정 (Optional)
     */
    @Override
    public Optional<Account> findByUsername(String username) {
        return this.accountQueryRepository.findOne(AugustSpecification.usernameEquals(username));
    }

    /**
     * ID로 계정을 찾는 메서드입니다.
     * @param id 계정 ID
     * @return 찾은 계정 (Optional)
     */
    @Override
    public Optional<Account> findById(String id) {
        return this.accountQueryRepository.findOne(AccountSpecification.idEquals(id));
    }

    /**
     * 모든 계정을 페이지로 반환하는 메서드입니다.
     * @param pageable 페이지 정보
     * @return 계정 페이지
     */
    @Override
    public Page<Account> findAll(Pageable pageable) {
        return this.accountQueryRepository.findAll(Specification.where(null), pageable);
    }

    /**
     * ID로 계정을 삭제하는 메서드입니다.
     * @param id 삭제할 계정의 ID
     */
    @Override
    public void deleteById(String id) {
        this.accountCommandRepository.deleteById(id);
    }

}