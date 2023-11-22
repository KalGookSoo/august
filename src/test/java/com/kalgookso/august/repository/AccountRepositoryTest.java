package com.kalgookso.august.repository;

import com.kalgookso.august.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AccountRepositoryTest {

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return Account when findById is called with existing id")
    void findByIdExistingId() {
        Account account = new Account();
        account.setId("test-id");
        when(accountRepository.findById("test-id")).thenReturn(Optional.of(account));

        Optional<Account> result = accountRepository.findById("test-id");

        assertTrue(result.isPresent());
        assertEquals(account, result.get());
    }

    @Test
    @DisplayName("Should return empty Optional when findById is called with non-existing id")
    void findByIdNonExistingId() {
        when(accountRepository.findById("test-id")).thenReturn(Optional.empty());

        Optional<Account> result = accountRepository.findById("test-id");

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should save and return Account when save is called")
    void saveAccount() {
        Account account = new Account();
        account.setId("test-id");
        when(accountRepository.save(account)).thenReturn(account);

        Account result = accountRepository.save(account);

        assertEquals(account, result);
    }

    @Test
    @DisplayName("Should call deleteById when deleteById is called with id")
    void deleteById() {
        doNothing().when(accountRepository).deleteById("test-id");

        accountRepository.deleteById("test-id");

        verify(accountRepository, times(1)).deleteById("test-id");
    }

    @Test
    @DisplayName("Should return Account when findByUsername is called with existing username")
    void findByUsernameExistingUsername() {
        Account account = new Account();
        account.setUsername("test-username");
        when(accountRepository.findByUsername("test-username")).thenReturn(Optional.of(account));

        Optional<Account> result = accountRepository.findByUsername("test-username");

        assertTrue(result.isPresent());
        assertEquals(account, result.get());
    }

    @Test
    @DisplayName("Should return empty Optional when findByUsername is called with non-existing username")
    void findByUsernameNonExistingUsername() {
        when(accountRepository.findByUsername("test-username")).thenReturn(Optional.empty());

        Optional<Account> result = accountRepository.findByUsername("test-username");

        assertTrue(result.isEmpty());
    }

}