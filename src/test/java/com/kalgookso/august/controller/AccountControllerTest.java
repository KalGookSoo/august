package com.kalgookso.august.controller;

import com.kalgookso.august.entity.Account;
import com.kalgookso.august.mapper.AccountMapper;
import com.kalgookso.august.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    @DisplayName("")
    void getAll() throws Exception {

        // 가짜 데이터 생성
        Account account = new Account("test", "test", "test");
        PageImpl<Account> accounts = new PageImpl<>(Collections.singletonList(account));

        // Mocking: findAll 메소드 호출 시 가짜 데이터 변환
        when(this.accountService.findAll(any(Pageable.class))).thenReturn(accounts);

        // GET /accounts 요청
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("accounts/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("page"))
                .andExpect(MockMvcResultMatchers.model().attribute("page", accounts.getContent()));
    }

}