package com.kalgookso.august.service;

import com.kalgookso.august.command.MenuCommand;
import com.kalgookso.august.entity.Menu;
import com.kalgookso.august.event.AclObjectCreatedEvent;
import com.kalgookso.august.event.AclObjectDeletedEvent;
import com.kalgookso.august.repository.MenuRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.kalgookso.august.specification.AugustSpecification.idEquals;

@Service
@Transactional
public class DefaultMenuService implements MenuService {

    private final MenuRepository menuRepository;

    private final ApplicationEventPublisher eventPublisher;

    public DefaultMenuService(MenuRepository menuRepository, ApplicationEventPublisher eventPublisher) {
        this.menuRepository = menuRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Menu create(Menu menu) {
        Menu createdMenu = menuRepository.save(menu);
        eventPublisher.publishEvent(new AclObjectCreatedEvent(Menu.class, createdMenu.getId(), createdMenu.getCreatedBy()));
        return createdMenu;
    }

    @Override
    public Menu update(String id, MenuCommand command) {
        Optional<Menu> foundMenu = findById(id);
        if (foundMenu.isEmpty()) {
            throw new NoSuchElementException("메뉴를 찾을 수 없습니다.");
        }
        Menu menu = foundMenu.get();
        menu.setName(command.getName());
        menu.setUrl(command.getUrl());
        return menu;
    }

    @Override
    public Optional<Menu> findById(String id) {
        return menuRepository.findOne(idEquals(id));
    }

    @Override
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        menuRepository.deleteById(id);
        eventPublisher.publishEvent(new AclObjectDeletedEvent(Menu.class, id));
    }

    @Override
    public void addChild(String menuId, Menu child) {
        Menu menu = menuRepository.findOne(idEquals(menuId))
                .orElseThrow(() -> new NoSuchElementException("메뉴를 찾을 수 없습니다."));
        menu.addChild(child);
    }

}