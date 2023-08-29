package com.kalgookso.august;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AugustApplication {

    public static void main(String[] args) {
        SpringApplication.run(AugustApplication.class, args);
    }

}
