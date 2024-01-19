package com.kalgookso.august.cms.repository;

import com.kalgookso.august.cms.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccountRepository extends JpaRepository<Account, String>, JpaSpecificationExecutor<Account> {
}
