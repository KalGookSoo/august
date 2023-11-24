package com.kalgookso.august.repository;

import com.kalgookso.august.entity.AclClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AclClassRepository extends JpaRepository<AclClass, Long>, JpaSpecificationExecutor<AclClass> {
}