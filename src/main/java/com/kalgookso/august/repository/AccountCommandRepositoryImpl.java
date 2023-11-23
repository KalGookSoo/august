package com.kalgookso.august.repository;

import com.kalgookso.august.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AccountCommandRepositoryImpl implements AccountCommandRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Account save(Account account) {
        if (account.getId() == null) {
            em.persist(account);
            return account;
        } else {
            return em.merge(account);
        }
    }

    @Override
    public void deleteById(String id) {
        Account account = em.find(Account.class, id);
        if (account != null) {
            em.remove(account);
        }
    }

}