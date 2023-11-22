package com.kalgookso.august;

import com.kalgookso.august.entity.Account;
import com.kalgookso.august.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AugustApplication implements CommandLineRunner {

    private final AccountService accountService;

    public AugustApplication(AccountService accountService) {
        this.accountService = accountService;
    }

    public static void main(String[] args) {
        SpringApplication.run(AugustApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Account account = new Account();
        account.setId("tester");
        account.setPassword("1234");
        account.setName("관리자");
        this.accountService.save(account);
    }

}