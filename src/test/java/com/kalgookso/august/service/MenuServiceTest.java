package com.kalgookso.august.service;

import com.kalgookso.august.command.MenuCommand;
import com.kalgookso.august.entity.Menu;
import com.kalgookso.august.repository.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class MenuServiceTest {

    private MenuService menuService;

    @Autowired
    private MenuRepository menuRepository;

    private Menu testMenu;

    @BeforeEach
    void setup() {

        ApplicationEventPublisher eventPublisher = mock(ApplicationEventPublisher.class);
        doNothing().when(eventPublisher).publishEvent(any());

        menuService = new DefaultMenuService(menuRepository, eventPublisher);

        Menu menu = new Menu();
        menu.setName("Test Menu");
        menu.setUrl("http://test.com");
        testMenu = menuService.create(menu);
    }

    @Test
    @DisplayName("메뉴를 생성합니다.")
    void createMenuTest() {
        Menu menu = new Menu();
        menu.setName("Test Menu");
        menu.setUrl("http://test.com");

        Menu createdMenu = menuService.create(menu);

        assertNotNull(createdMenu);
        assertEquals(menu.getName(), createdMenu.getName());
        assertEquals(menu.getUrl(), createdMenu.getUrl());
    }

    @Test
    @DisplayName("메뉴를 수정합니다.")
    void updateMenuTest() {
        MenuCommand command = new MenuCommand();
        command.setName("Updated Menu");
        command.setUrl("http://updated.com");

        Menu updatedMenu = menuService.update(testMenu.getId(), command);
        assertEquals(command.getName(), updatedMenu.getName());
        assertEquals(command.getUrl(), updatedMenu.getUrl());
    }

    @Test
    @DisplayName("메뉴를 식별자로 조회합니다.")
    void findByIdTest() {
        menuService.findById(testMenu.getId()).ifPresentOrElse(
                menu -> {
                    assertEquals(testMenu.getId(), menu.getId());
                    assertEquals(testMenu.getName(), menu.getName());
                    assertEquals(testMenu.getUrl(), menu.getUrl());
                },
                () -> fail("메뉴를 찾을 수 없습니다."));
    }

    @Test
    @DisplayName("모든 메뉴를 조회합니다.")
    void findAllTest() {
        List<Menu> menus = menuService.findAll();
        assertFalse(menus.isEmpty());
    }

    @Test
    @DisplayName("메뉴를 식별자로 삭제합니다.")
    void deleteByIdTest() {
        menuService.deleteById(testMenu.getId());
        assertFalse(menuService.findById(testMenu.getId()).isPresent());
    }

    @Test
    @DisplayName("메뉴에 자식 메뉴를 추가합니다.")
    void addChildTest() {
        Menu child = new Menu();
        child.setName("Child Menu");
        child.setUrl("http://child.com");
        menuService.addChild(testMenu.getId(), child);

        List<Menu> children = testMenu.getChildren();
        assertEquals(1, children.size());
    }

}