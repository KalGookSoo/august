package com.kalgookso.august.repository;

import com.kalgookso.august.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 계정 명령 저장소 구현 클래스입니다.
 * 이 클래스는 AccountCommandRepository 인터페이스를 구현하며, EntityManager를 사용하여 계정에 대한 생성, 수정, 삭제 명령을 처리합니다.
 */
public class AccountCommandRepositoryImpl implements AccountCommandRepository {

    /**
     * EntityManager는 JPA의 핵심 인터페이스로, 엔티티를 관리합니다.
     * @PersistenceContext 어노테이션을 사용하여 스프링 컨테이너에서 EntityManager를 주입받습니다.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * 주어진 Account 엔티티를 저장하는 메서드입니다.
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
     * 주어진 ID에 해당하는 Account 엔티티를 삭제하는 메서드입니다.
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

}