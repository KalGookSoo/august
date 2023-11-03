package com.kalgookso.august.service;

import com.kalgookso.august.command.AccountCommand;
import com.kalgookso.august.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * 계정 서비스
 */
public interface AccountService {

    /**
     * 계정을 저장합니다.
     * @param command 계정 생성 커맨드
     * @return 저장된 계정
     */
    Account create(AccountCommand.Post command);

    /**
     * 계정을 저장합니다.
     * @param account 계정
     * @return 저장된 계정
     */
    Account save(Account account);

    /**
     * 계정명에 해당하는 계정을 반환합니다.
     * @param username 계정명
     * @return 계정
     */
    Optional<Account> findByUsername(String username);

    /**
     * 식별자에 해당하는 계정을 반환합니다.
     * @param id 식별자
     * @return 계정
     */
    Optional<Account> findById(String id);

    /**
     * 계정 전체를 조회합니다.
     * @param pageable 페이징 정보
     * @return 계정 목록
     */
    Page<Account> findAll(Pageable pageable);

    /**
     * 계정을 삭제합니다.
     * @param id 식별자
     */
    void deleteById(String id);

    /**
     * 패스워드가 일치하는지 확인합니다.
     * @param rawPassword     패스워드
     * @param encodedPassword 인코딩된 패스워드
     * @return 일치 여부
     */
    boolean isMatch(CharSequence rawPassword, String encodedPassword);

    /**
     * 패스워드를 인코딩합니다.
     * @param rawPassword 패스워드
     * @return 인코딩된 패스워드
     */
    String encode(CharSequence rawPassword);

    /**
     * 계정에 커맨드 내용을 병합합니다.
     * @param account 계정
     * @param command 계정 커맨드
     * @return 계정
     */
    Account convert(Account account, AccountCommand.Put command);

}