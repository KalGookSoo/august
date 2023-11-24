package com.kalgookso.august.repository;

import com.kalgookso.august.entity.AclEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AclEntrysRepository extends JpaRepository<AclEntry, Long>, JpaSpecificationExecutor<AclEntry> {
}