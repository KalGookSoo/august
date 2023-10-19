package com.kalgookso.august;

import com.kalgookso.august.command.AccountCommand;
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
        AccountCommand.Post command = new AccountCommand.Post("tester", "1234", "관리자");
        this.accountService.create(command);
    }

}