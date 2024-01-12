package com.kalgookso.august.service;

import com.kalgookso.august.command.AccountUpdateCommand;
import com.kalgookso.august.criteria.AccountCriteria;
import com.kalgookso.august.entity.account.Account;
import com.kalgookso.august.entity.account.Authority;
import com.kalgookso.august.exception.UsernameAlreadyExistsException;
import com.kalgookso.august.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

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
        Account account = createDummyEntity("tester", "1234", "테스터", "ROLE_USER");
        testAccount = accountService.create(account);
    }

    private Account createDummyEntity(String username, String password, String name, String authorityName) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setName(name);
        account.getAuthorities().add(new Authority(authorityName));
        return account;
    }

    @Test
    @DisplayName("계정을 생성합니다.")
    public void createAccountTest() {
        // Given
        Account account = createDummyEntity("tester2", "1234", "테스터2", "ROLE_USER");

        try {
            // When
            Account createdAccount = accountService.create(account);

            // Then
            assertNotNull(createdAccount);
        } catch (UsernameAlreadyExistsException e) {
            fail();
        }
    }

    @Test
    @DisplayName("계정 생성 시 이미 존재하는 아이디를 입력하면 UsernameAlreadyExistsException 예외를 발생시킵니다.")
    public void createAccountWithExistingUsernameTest() {
        // Given
        Account account = createDummyEntity("tester2", "1234", "테스터2", "ROLE_USER");
        accountService.create(account);

        Account invalidAccount = createDummyEntity("tester2", "1234", "테스터2", "ROLE_USER");

        // Then
        assertThrows(UsernameAlreadyExistsException.class, () -> accountService.create(invalidAccount));
    }

    @Test
    @DisplayName("계정 생성 시 계정 정책 날짜를 확인합니다.")
    public void createAccountWithPolicyTest() {
        // Given
        Account account = createDummyEntity("tester2", "1234", "테스터2", "ROLE_USER");

        try {
            // When
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

    @Test
    @DisplayName("계정 정보를 업데이트합니다.")
    public void updateAccountTest() {
        // Given
        AccountUpdateCommand command = new AccountUpdateCommand();
        command.setName("테스터 업데이트");
        command.setEmailId("updated");
        command.setEmailDomain("email.com");
        command.setFirstContactNumber("010");
        command.setMiddleContactNumber("1234");
        command.setLastContactNumber("5678");

        // When
        Account updatedAccount = accountService.update(testAccount.getId(), command);

        // Then
        assertEquals(command.getName(), updatedAccount.getName());
        assertEquals(command.getEmailId() + "@" + command.getEmailDomain(), updatedAccount.getEmail().getValue());
        assertEquals(command.getFirstContactNumber() + "-" + command.getMiddleContactNumber() + "-" + command.getLastContactNumber(), updatedAccount.getContactNumber().getValue());
    }

    @Test
    @DisplayName("계정을 ID로 찾습니다.")
    public void findByIdTest() {
        // Given
        String id = testAccount.getId();

        // When
        Optional<Account> foundAccount = accountService.findById(id);

        // Then
        assertTrue(foundAccount.isPresent());
        assertEquals(id, foundAccount.get().getId());
    }

    @Test
    @DisplayName("페이지네이션 정보에 기반한 계정 목록을 조회합니다.")
    public void findAllTest() {
        // Given
        PageRequest pageRequest = PageRequest.of(0, 10);

        // When
        Page<Account> accounts = accountService.findAll(pageRequest);

        // Then
        assertNotNull(accounts);
        assertTrue(accounts.getTotalElements() > 0);
    }

    @Test
    @DisplayName("페이지네이션 정보와 검색 조건에 기반한 계정 목록을 조회합니다.")
    public void findAllWithCriteriaTest() {
        // Given
        PageRequest pageRequest = PageRequest.of(0, 10);
        AccountCriteria criteria = new AccountCriteria("tester", null, null, null);

        // When
        Page<Account> accounts = accountService.findAll(criteria, pageRequest);

        // Then
        assertNotNull(accounts);
        assertTrue(accounts.getTotalElements() > 0);
    }


    @Test
    @DisplayName("계정을 ID로 삭제합니다.")
    public void deleteByIdTest() {
        // Given
        String id = testAccount.getId();

        // When
        accountService.deleteById(id);

        // Then
        Optional<Account> deletedAccount = accountService.findById(id);
        assertTrue(deletedAccount.isEmpty());
    }

    @Test
    @DisplayName("계정의 패스워드를 업데이트합니다.")
    public void updatePasswordTest() {
        // Given
        String id = testAccount.getId();
        String newPassword = "newPassword";

        // When
        accountService.updatePassword(id, newPassword);

        // Then
        Optional<Account> updatedAccount = accountService.findById(id);
        assertTrue(updatedAccount.isPresent());
        assertTrue(passwordEncoder.matches(newPassword, updatedAccount.get().getPassword()));
    }

}