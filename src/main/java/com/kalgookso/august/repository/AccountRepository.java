package com.kalgookso.august.repository;

import com.kalgookso.august.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * 계정 저장소
 */
public interface AccountRepository extends Repository<Account, String>, JpaSpecificationExecutor<Account> {

    /**
     * 계정을 저장합니다.
     * @param account 계정
     * @return 저장된 계정
     */
    Account save(Account account);

    /**
     * 계정명으로 계정을 조회합니다.
     * @param username 계정명
     * @return 계정
     */
    Optional<Account> findByUsername(String username);

    /**
     * 계정 식별자로 계정을 조회합니다.
     * @param id 계정 식별자
     * @return 계정
     */
    Optional<Account> findById(String id);

    /**
     * 계정을 수정합니다.
     * @param id 계정 식별자
     * @param account 계정
     * @return 수정된 계정
     */
    Account updateById(String id, Account account);

    /**
     * 계정을 삭제합니다.
     * @param id 계정 식별자
     */
    void deleteById(String id);

    /**
     * 계정 전체를 조회합니다.
     * @param pageable 페이징 정보
     * @return 계정 목록
     */
    Page<Account> findAll(Pageable pageable);

    /**
     * 계정 전체를 조회합니다.
     * @param spec 검색 조건
     * @param pageable 페이징 정보
     * @return 계정 목록
     */
    Page<Account> findAll(Specification<Account> spec, Pageable pageable);

}
