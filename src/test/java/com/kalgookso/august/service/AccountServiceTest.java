package com.kalgookso.august.service;

import com.kalgookso.august.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AccountServiceTest {

    @Mock
    private AccountService accountService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("계정생성 성공")
    public void createAccountSuccess() {
        Account account = new Account();
        account.setUsername("test");
        account.setPassword("password");

        when(accountService.create(any())).thenReturn(account);

        Account result = accountService.create(account);

        assertEquals("test", result.getUsername());
        assertEquals("password", result.getPassword());
    }

    @Test
    @DisplayName("계정저장 성공")
    public void saveAccountSuccess() {
        Account account = new Account();
        account.setUsername("test");
        account.setPassword("password");

        when(accountService.save(any())).thenReturn(account);

        Account result = accountService.save(account);

        assertEquals("test", result.getUsername());
        assertEquals("password", result.getPassword());
    }

    @Test
    @DisplayName("계정찾기 성공 사용자이름으로")
    public void findAccountByUsernameSuccess() {
        Account account = new Account();
        account.setUsername("test");
        account.setPassword("password");

        when(accountService.findByUsername(any())).thenReturn(Optional.of(account));

        Optional<Account> result = accountService.findByUsername("test");

        assertTrue(result.isPresent());
        assertEquals("test", result.get().getUsername());
    }

    @Test
    @DisplayName("계정찾기 실패 사용자이름으로")
    public void findAccountByUsernameFailure() {
        when(accountService.findByUsername(any())).thenReturn(Optional.empty());

        Optional<Account> result = accountService.findByUsername("test");

        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("계정찾기 성공 ID로")
    public void findAccountByIdSuccess() {
        Account account = new Account();
        account.setId("1");
        account.setPassword("password");

        when(accountService.findById(any())).thenReturn(Optional.of(account));

        Optional<Account> result = accountService.findById("1");

        assertTrue(result.isPresent());
        assertEquals("1", result.get().getId());
    }

    @Test
    @DisplayName("계정찾기 실패 ID로")
    public void findAccountByIdFailure() {
        when(accountService.findById(any())).thenReturn(Optional.empty());

        Optional<Account> result = accountService.findById("1");

        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("계정삭제 성공")
    public void deleteAccountSuccess() {
        Account account = new Account();
        account.setId("1");
        account.setPassword("password");

        accountService.deleteById("1");

        when(accountService.findById(any())).thenReturn(Optional.empty());

        Optional<Account> result = accountService.findById("1");

        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("비밀번호변경 성공")
    public void changePasswordSuccess() {
        Account account = new Account();
        account.setId("1");
        account.setPassword("password");

        when(accountService.updatePassword(any(), any())).thenReturn(account);

        Account result = accountService.updatePassword("1", "newPassword");

        assertEquals("password", result.getPassword());
    }
}