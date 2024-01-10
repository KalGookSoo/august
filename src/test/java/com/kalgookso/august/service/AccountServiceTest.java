package com.kalgookso.august.service;

import com.kalgookso.august.entity.account.Account;
import com.kalgookso.august.entity.account.Authority;
import com.kalgookso.august.exception.UsernameAlreadyExistsException;
import com.kalgookso.august.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
public class AccountServiceTest {

    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Account testAccount;

    @BeforeEach
    public void setup() {
        accountService = new DefaultAccountService(accountRepository, passwordEncoder);
        Account account = new Account();
        account.setUsername("tester");
        account.setPassword("1234");
        account.setName("테스터");
        account.getAuthorities().add(new Authority("ROLE_USER"));
        try {
            testAccount = accountService.create(account);
        } catch (UsernameAlreadyExistsException e) {
            fail();
        }
    }

    @Test
    @DisplayName("계정을 생성합니다.")
    public void createAccountTest() {
        Account account = new Account();
        account.setUsername("tester2");
        account.setPassword("1234");
        account.setName("테스터2");
        account.getAuthorities().add(new Authority("ROLE_USER"));
        try {
            Account createdAccount = accountService.create(account);
            assertNotNull(createdAccount);
        } catch (UsernameAlreadyExistsException e) {
            fail();
        }
    }

    @Test
    @DisplayName("계정 생성 시 이미 존재하는 아이디를 입력하면 UsernameAlreadyExistsException 예외를 발생시킵니다.")
    public void createAccountWithExistingUsernameTest() {

        Account account = new Account();
        account.setUsername("tester2");
        account.setPassword("1234");
        account.setName("테스터2");
        account.getAuthorities().add(new Authority("ROLE_USER"));
        try {
            Account createdAccount = accountService.create(account);
            assertNotNull(createdAccount);
        } catch (UsernameAlreadyExistsException e) {
            fail();
        }

        Account invalidAccount = new Account();
        invalidAccount.setUsername("tester");
        invalidAccount.setPassword("1234");
        invalidAccount.setName("테스터2");
        invalidAccount.getAuthorities().add(new Authority("ROLE_USER"));

        assertThrows(UsernameAlreadyExistsException.class, () -> accountService.create(invalidAccount));
    }

    @Test
    @DisplayName("계정 생성 시 계정 정책 날짜를 확인합니다.")
    public void createAccountWithPolicyTest() {
        Account account = new Account();
        account.setUsername("tester2");
        account.setPassword("1234");
        account.setName("테스터2");
        account.getAuthorities().add(new Authority("ROLE_USER"));
        try {
            Account createdAccount = accountService.create(account);

            // Then
            LocalDateTime expectedExpiredAt = LocalDate.now().atTime(LocalTime.MIDNIGHT).plusYears(2L);
            LocalDateTime expectedCredentialsExpiredAt = LocalDate.now().atTime(LocalTime.MIDNIGHT).plusDays(180L);
            assertEquals(expectedExpiredAt, createdAccount.getExpiredAt());
            assertEquals(expectedCredentialsExpiredAt, createdAccount.getCredentialsExpiredAt());
        } catch (UsernameAlreadyExistsException e) {
            fail();
        }
    }

}