package com.kalgookso.august;

import com.kalgookso.august.entity.Account;
import com.kalgookso.august.entity.Authority;
import com.kalgookso.august.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

/**
 * AugustApplication 클래스입니다.
 * 이 클래스는 스프링 부트 애플리케이션의 진입점입니다.
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class AugustApplication implements CommandLineRunner {

    private final AccountService accountService;  // 계정 서비스

    private final PasswordEncoder passwordEncoder;  // 비밀번호 인코더

    /**
     * AugustApplication 생성자입니다.
     * @param accountService 계정 서비스
     * @param passwordEncoder 비밀번호 인코더
     */
    public AugustApplication(AccountService accountService, PasswordEncoder passwordEncoder) {
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 애플리케이션의 메인 메서드입니다.
     * @param args 커맨드라인 인자
     */
    public static void main(String[] args) {
        SpringApplication.run(AugustApplication.class, args);
    }

    /**
     * 애플리케이션 실행 후 호출되는 메서드입니다.
     * 이 메서드에서는 테스트 계정을 생성하고 저장합니다.
     * @param args 커맨드라인 인자
     */
    @Override
    public void run(String... args) {
        Optional<Account> foundAccount = this.accountService.findByUsername("tester");
        if (foundAccount.isEmpty()) {
            Account account = new Account();
            account.setUsername("tester");
            account.setPassword(this.passwordEncoder.encode("1234"));
            account.setName("관리자");
            account.getAuthorities().add(new Authority("ROLE_ADMIN"));
            this.accountService.save(account);
        }
    }

}