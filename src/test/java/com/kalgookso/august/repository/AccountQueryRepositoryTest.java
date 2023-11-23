package com.kalgookso.august.repository;

import com.kalgookso.august.entity.Account;
import com.kalgookso.august.specification.AccountSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * 계정 쿼리 저장소 테스트 클래스입니다.
 * 이 클래스는 AccountQueryRepository 인터페이스의 메서드를 테스트합니다.
 */
class AccountQueryRepositoryTest {

    @Mock
    private AccountQueryRepository accountQueryRepository;  // 계정 쿼리 저장소

    /**
     * 각 테스트 메서드 실행 전에 호출되는 메서드입니다.
     * 이 메서드에서는 Mockito 어노테이션을 초기화합니다.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 존재하는 ID로 findById 메서드를 호출했을 때 계정을 반환하는지 테스트합니다.
     */
    @Test
    @DisplayName("존재하는 ID로 findById를 호출하면 계정을 반환해야 합니다.")
    void findByIdExistingId() {
        Account account = new Account();
        account.setId("test-id");
        when(accountQueryRepository.findOne(AccountSpecification.idEquals("test-id"))).thenReturn(Optional.of(account));

        Optional<Account> result = accountQueryRepository.findOne(AccountSpecification.idEquals("test-id"));

        assertTrue(result.isPresent());
        assertEquals(account, result.get());
    }

    /**
     * 존재하지 않는 ID로 findById 메서드를 호출했을 때 빈 Optional을 반환하는지 테스트합니다.
     */
    @Test
    @DisplayName("존재하지 않는 ID로 findById를 호출하면 빈 Optional을 반환해야 합니다.")
    void findByIdNonExistingId() {
        when(accountQueryRepository.findOne(AccountSpecification.idEquals("test-id"))).thenReturn(Optional.empty());

        Optional<Account> result = accountQueryRepository.findOne(AccountSpecification.idEquals("test-id"));

        assertTrue(result.isEmpty());
    }

    /**
     * 존재하는 사용자 이름으로 findByUsername 메서드를 호출했을 때 계정을 반환하는지 테스트합니다.
     */
    @Test
    @DisplayName("존재하는 사용자 이름으로 findByUsername를 호출하면 계정을 반환해야 합니다.")
    void findByUsernameExistingUsername() {
        Account account = new Account();
        account.setUsername("test-username");
        when(accountQueryRepository.findOne(AccountSpecification.usernameEquals("test-username"))).thenReturn(Optional.of(account));

        Optional<Account> result = accountQueryRepository.findOne(AccountSpecification.usernameEquals("test-username"));

        assertTrue(result.isPresent());
        assertEquals(account, result.get());
    }

    /**
     * 존재하지 않는 사용자 이름으로 findByUsername 메서드를 호출했을 때 빈 Optional을 반환하는지 테스트합니다.
     */
    @Test
    @DisplayName("존재하지 않는 사용자 이름으로 findByUsername를 호출하면 빈 Optional을 반환해야 합니다.")
    void findByUsernameNonExistingUsername() {
        when(accountQueryRepository.findOne(AccountSpecification.usernameEquals("test-username"))).thenReturn(Optional.empty());

        Optional<Account> result = accountQueryRepository.findOne(AccountSpecification.usernameEquals("test-username"));

        assertTrue(result.isEmpty());
    }

}