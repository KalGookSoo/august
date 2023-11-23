package com.kalgookso.august.repository;

import com.kalgookso.august.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * 계정 명령 저장소 테스트 클래스입니다.
 * 이 클래스는 AccountCommandRepository 인터페이스의 메서드를 테스트합니다.
 */
class AccountCommandRepositoryTest {

    @Mock
    private AccountCommandRepository accountCommandRepository;  // 계정 명령 저장소

    /**
     * 각 테스트 메서드 실행 전에 호출되는 메서드입니다.
     * 이 메서드에서는 Mockito 어노테이션을 초기화합니다.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * save 메서드를 테스트하는 메서드입니다.
     * save 메서드가 계정을 저장하고 반환하는지 확인합니다.
     */
    @Test
    @DisplayName("save가 호출될 때 계정을 저장하고 반환해야 합니다.")
    void saveAccount() {
        Account account = new Account();
        account.setId("test-id");
        when(accountCommandRepository.save(account)).thenReturn(account);

        Account result = accountCommandRepository.save(account);

        assertEquals(account, result);
    }

    /**
     * deleteById 메서드를 테스트하는 메서드입니다.
     * deleteById 메서드가 ID로 계정을 삭제하는지 확인합니다.
     */
    @Test
    @DisplayName("deleteById가 ID로 계정을 삭제해야 합니다.")
    void deleteById() {
        doNothing().when(accountCommandRepository).deleteById("test-id");

        accountCommandRepository.deleteById("test-id");

        verify(accountCommandRepository, times(1)).deleteById("test-id");
    }

}