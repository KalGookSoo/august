package com.kalgookso.august.service;

import com.kalgookso.august.command.MenuCommand;
import com.kalgookso.august.entity.Menu;

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