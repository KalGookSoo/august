package com.kalgookso.august.repository;

import com.kalgookso.august.entity.Account;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

public interface AccountQueryRepository extends Repository<Account, String>, JpaSpecificationExecutor<Account> {

}