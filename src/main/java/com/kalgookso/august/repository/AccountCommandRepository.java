package com.kalgookso.august.repository;

import com.kalgookso.august.entity.Account;
import org.springframework.data.repository.Repository;

/**
 * 계정 명령 저장소 인터페이스입니다.
 * 이 인터페이스는 계정에 대한 생성, 수정, 삭제와 같은 명령을 처리합니다.
 */
public interface AccountCommandRepository extends Repository<Account, String> {

    /**
     * 계정을 저장하는 메서드입니다.
     * @param account 저장할 계정
     * @return 저장된 계정
     */
    Account save(Account account);

    /**
     * ID로 계정을 삭제하는 메서드입니다.
     * @param id 삭제할 계정의 ID
     */
    void deleteById(String id);
}