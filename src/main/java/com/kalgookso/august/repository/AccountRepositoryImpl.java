package com.kalgookso.august.repository;

import com.kalgookso.august.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

/**
 * AccountRepositoryImpl 클래스는 AccountRepository 인터페이스를 구현하는 클래스입니다.
 * 이 클래스는 JPA의 EntityManager를 사용하여 Account 엔티티에 대한 CRUD 작업을 수행합니다.
 */
public class AccountRepositoryImpl implements AccountRepository {

    /**
     * EntityManager는 JPA의 핵심 인터페이스로, 엔티티를 관리합니다.
     * @PersistenceContext 어노테이션을 사용하여 스프링 컨테이너에서 EntityManager를 주입받습니다.
     */
    @SuppressWarnings("JavadocDeclaration")
    @PersistenceContext
    private EntityManager em;

    /**
     * 주어진 ID에 해당하는 Account 엔티티를 찾습니다.
     *
     * @param id 찾을 Account 엔티티의 ID.
     * @return 찾은 Account 엔티티를 포함하는 Optional, 또는 Account 엔티티를 찾지 못한 경우 빈 Optional.
     */
    @Override
    public Optional<Account> findById(String id) {
        return Optional.ofNullable(em.find(Account.class, id));
    }

    /**
     * 주어진 Account 엔티티를 저장합니다.
     * 만약 Account 엔티티의 ID가 null이라면, 엔티티를 새로 생성하고,
     * 그렇지 않다면 기존의 엔티티를 업데이트합니다.
     *
     * @param account 저장할 Account 엔티티.
     * @return 저장된 Account 엔티티.
     */
    @Override
    public Account save(Account account) {
        if (account.getId() == null) {
            em.persist(account);
            return account;
        } else {
            return em.merge(account);
        }
    }

    /**
     * 주어진 ID에 해당하는 Account 엔티티를 삭제합니다.
     * 만약 Account 엔티티를 찾지 못한다면 아무 작업도 수행하지 않습니다.
     *
     * @param id 삭제할 Account 엔티티의 ID.
     */
    @Override
    public void deleteById(String id) {
        Account account = em.find(Account.class, id);
        if (account != null) {
            em.remove(account);
        }
    }

    /**
     * 주어진 사용자 이름에 해당하는 Account 엔티티를 찾습니다.
     *
     * @param username 찾을 Account 엔티티의 사용자 이름.
     * @return 찾은 Account 엔티티를 포함하는 Optional, 또는 Account 엔티티를 찾지 못한 경우 빈 Optional.
     */
    @Override
    public Optional<Account> findByUsername(String username) {
        return em.createQuery("select a from Account a where a.username = :username", Account.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst();
    }

}