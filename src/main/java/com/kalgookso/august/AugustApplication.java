package com.kalgookso.august;

import com.kalgookso.august.entity.account.Account;
import com.kalgookso.august.entity.account.Authority;
import com.kalgookso.august.exception.UsernameAlreadyExistsException;
import com.kalgookso.august.service.AccountService;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 이 클래스는 스프링 부트 애플리케이션의 진입점입니다.
 */
@SpringBootApplication
@EnableAsync
public class AugustApplication implements CommandLineRunner {

    private final DataSource dataSource;

    private final AccountService accountService;

    private final Logger logger = LoggerFactory.getLogger(AugustApplication.class);

    public AugustApplication(DataSource dataSource, AccountService accountService) {
        this.dataSource = dataSource;
        this.accountService = accountService;
    }

    public static void main(String[] args) {
        SpringApplication.run(AugustApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (((HikariDataSource) dataSource).getDriverClassName().contains("h2")) {
            executeSqlScript();
        }
        Account account = new Account();
        account.setUsername("admin");
        account.setPassword("1234");
        account.setName("관리자");
        account.getAuthorities().add(new Authority("ROLE_ADMIN"));
        try {
            this.accountService.create(account);
        } catch (UsernameAlreadyExistsException e) {
            logger.info("Username already exists");
        }
    }

    private void executeSqlScript() {
        Resource resource = new ClassPathResource("scripts/createAclSchema.sql");
        try {
            ScriptUtils.executeSqlScript(dataSource.getConnection(), resource);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute SQL script: " + e.getMessage(), e);
        }
    }

}