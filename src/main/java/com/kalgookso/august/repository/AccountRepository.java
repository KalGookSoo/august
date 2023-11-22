package com.kalgookso.august.repository;

import com.kalgookso.august.entity.Account;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * AccountRepository는 Repository와 JpaSpecificationExecutor 인터페이스를 확장하는 인터페이스입니다.
 * 이 인터페이스는 Account 엔티티에 대한 기본적인 CRUD 작업과 JPA 명세를 사용한 쿼리 작업을 제공합니다.
 */
public interface AccountRepository extends Repository<Account, String> {

    /**
     * 주어진 ID에 해당하는 Account 엔티티를 찾습니다.
     *
     * @param id 찾을 Account 엔티티의 ID.
     * @return 찾은 Account 엔티티를 포함하는 Optional, 또는 Account 엔티티를 찾지 못한 경우 빈 Optional.
     */
    Optional<Account> findById(String id);

    /**
     * 주어진 Account 엔티티를 저장합니다.
     *
     * @param account 저장할 Account 엔티티.
     * @return 저장된 Account 엔티티.
     */
    Account save(Account account);

    /**
     * 주어진 ID에 해당하는 Account 엔티티를 삭제합니다.
     *
     * @param id 삭제할 Account 엔티티의 ID.
     */
    void deleteById(String id);

    /**
     * 주어진 사용자 이름에 해당하는 Account 엔티티를 찾습니다.
     *
     * @param username 찾을 Account 엔티티의 사용자 이름.
     * @return 찾은 Account 엔티티를 포함하는 Optional, 또는 Account 엔티티를 찾지 못한 경우 빈 Optional.
     */
    Optional<Account> findByUsername(String username);

}