package com.kalgookso.august.cms.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("계정 목록이 담긴 뷰를 반환합니다.")
    @WithMockUser(username = "test", password = "test", roles = "USER")
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("accounts/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("pagedModel"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("계정 생성을 위한 뷰를 반환합니다.")
    @WithMockUser(username = "test", password = "test", roles = "USER")
    void getNew() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("accounts/new"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("계정 정보가 담긴 뷰를 반환합니다.")
    @WithMockUser(username = "test", password = "test", roles = "USER")
    void getOne() throws Exception {

    }

    @Test
    @DisplayName("계정을 생성합니다.")
    @WithMockUser(username = "test", password = "test", roles = "USER")
    void create() throws Exception {

    }

    @Test
    @DisplayName("계정 정보를 수정하기 위한 뷰를 반환합니다.")
    @WithMockUser(username = "test", password = "test", roles = "USER")
    void getEdit() throws Exception {

    }

    @Test
    @DisplayName("계정 정보를 수정합니다.")
    @WithMockUser(username = "test", password = "test", roles = "USER")
    void update() throws Exception {

    }

    @Test
    @DisplayName("계정을 삭제합니다.")
    @WithMockUser(username = "test", password = "test", roles = "USER")
    void delete() throws Exception {

    }

    @Test
    @DisplayName("계정 패스워드를 수정하기 위한 뷰를 반환합니다.")
    @WithMockUser(username = "test", password = "test", roles = "USER")
    void getEditPassword() throws Exception {

    }

    @Test
    @DisplayName("계정 패스워드를 수정합니다.")
    @WithMockUser(username = "test", password = "test", roles = "USER")
    void updatePassword() throws Exception {

    }

}