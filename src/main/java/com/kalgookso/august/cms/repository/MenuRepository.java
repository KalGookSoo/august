package com.kalgookso.august.cms.repository;

import com.kalgookso.august.cms.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MenuRepository extends JpaRepository<Menu, String>, JpaSpecificationExecutor<Menu> {
}