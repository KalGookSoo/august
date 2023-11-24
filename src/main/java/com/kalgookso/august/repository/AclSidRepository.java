package com.kalgookso.august.repository;

import com.kalgookso.august.entity.AclSid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AclSidRepository extends JpaRepository<AclSid, Long>, JpaSpecificationExecutor<AclSid> {
}