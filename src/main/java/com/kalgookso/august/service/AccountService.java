package com.kalgookso.august.service;

import com.kalgookso.august.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * 계정 서비스 인터페이스입니다.
 * 이 인터페이스는 계정 관련 작업을 정의합니다.
 */
public interface AccountService {

    /**
     * 계정을 생성하는 메서드입니다.
     * @param account 생성할 계정
     * @return 생성된 계정
     */
    Account create(Account account);

    /**
     * 계정을 저장하는 메서드입니다.
     * @param account 저장할 계정
     * @return 저장된 계정
     */
    Account save(Account account);

    /**
     * 사용자 이름으로 계정을 찾는 메서드입니다.
     * @param username 사용자 이름
     * @return 찾은 계정 (Optional)
     */
    Optional<Account> findByUsername(String username);

    /**
     * ID로 계정을 찾는 메서드입니다.
     * @param id 계정 ID
     * @return 찾은 계정 (Optional)
     */
    Optional<Account> findById(String id);

    /**
     * 모든 계정을 페이지로 반환하는 메서드입니다.
     * @param pageable 페이지 정보
     * @return 계정 페이지
     */
    Page<Account> findAll(Pageable pageable);

    /**
     * ID로 계정을 삭제하는 메서드입니다.
     * @param id 삭제할 계정의 ID
     */
    void deleteById(String id);

    /**
     * 계정의 비밀번호를 변경하는 메서드입니다.
     * @param id 계정 ID
     * @param password 변경할 비밀번호
     */
    void changePassword(String id, String password);

}