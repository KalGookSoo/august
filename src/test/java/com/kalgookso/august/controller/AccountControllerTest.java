package com.kalgookso.august.controller;

import com.kalgookso.august.entity.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(username = "test", password = "test", roles = "USER")
    @Test
    @DisplayName("계정 목록 페이지를 반환합니다.")
    void getAll() throws Exception {

        // 가짜 데이터 생성
        Account account = new Account("test", "test", "test");
        PageImpl<Account> accounts = new PageImpl<>(Collections.singletonList(account));

        mockMvc.perform(MockMvcRequestBuilders.get("/accounts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("accounts/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("pagedModel"))
                .andDo(MockMvcResultHandlers.print());
    }

}