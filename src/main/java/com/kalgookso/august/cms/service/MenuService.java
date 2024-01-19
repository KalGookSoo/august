package com.kalgookso.august.cms.service;

import com.kalgookso.august.cms.command.MenuCommand;
import com.kalgookso.august.cms.entity.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuService {

    Menu create(Menu menu);

    Menu update(String id, MenuCommand command);

    Optional<Menu> findById(String id);

    List<Menu> findAll();

    void deleteById(String id);

    void addChild(String menuId, Menu child);

}