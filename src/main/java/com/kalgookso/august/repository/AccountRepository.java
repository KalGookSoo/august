package com.kalgookso.august.repository;

import com.kalgookso.august.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * 계정 저장소
 */
public interface AccountRepository extends JpaRepository<Account, String>, JpaSpecificationExecutor<Account> {

    /**
     * 계정명으로 계정을 조회합니다.
     * @param username 계정명
     * @return 계정
     */
    Optional<Account> findByUsername(String username);

}
