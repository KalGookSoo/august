package com.kalgookso.august.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.Nonnull;
import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class SecurityAuditorAware implements AuditorAware<String> {

    @Override
    @Nonnull
    public Optional<String> getCurrentAuditor() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return Optional.ofNullable(username);
    }

}