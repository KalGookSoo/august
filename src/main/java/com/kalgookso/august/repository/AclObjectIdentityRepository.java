package com.kalgookso.august.repository;

import com.kalgookso.august.entity.AclObjectIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AclObjectIdentityRepository extends JpaRepository<AclObjectIdentity, Long>, JpaSpecificationExecutor<AclObjectIdentity> {
}