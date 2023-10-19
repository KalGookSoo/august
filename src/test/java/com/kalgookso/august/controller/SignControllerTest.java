package com.kalgookso.august.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class SignControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void beforeAll() {
        System.out.println("SignControllerTest.beforeAll");
    }

    @BeforeEach
    void setUp() {
        System.out.println("SignControllerTest.setUp");
    }

    @Test
    @DisplayName("로그인 페이지를 반환합니다.")
    public void testSignInPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/sign-in"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("sign-in"));
    }

    @Test
    @DisplayName("회원가입 페이지를 반환합니다.")
    public void testSignUpPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/sign-up"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("sign-up"));
    }

    @AfterEach
    void tearDown() {
        System.out.println("SignControllerTest.tearDown");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("SignControllerTest.afterAll");
    }

}