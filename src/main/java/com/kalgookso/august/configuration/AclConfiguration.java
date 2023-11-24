package com.kalgookso.august.configuration;

import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.acls.domain.*;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.AclCache;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.sql.DataSource;

@Configuration
public class AclConfiguration {

    private final DataSource dataSource;

    private final LookupStrategy lookupStrategy;

    public AclConfiguration(DataSource dataSource, LookupStrategy lookupStrategy) {
        this.dataSource = dataSource;
        this.lookupStrategy = lookupStrategy;
    }

    @Bean
    public MutableAclService mutableAclService() {
        return new JdbcMutableAclService(dataSource, lookupStrategy, this.aclCache());
    }

    @Bean
    public LookupStrategy lookupStrategy() {
        return new BasicLookupStrategy(dataSource, this.aclCache(), this.aclAuthorizationStrategy(), new ConsoleAuditLogger());
    }

    @Bean
    public AclCache aclCache() {
        return new SpringCacheBasedAclCache(
            new ConcurrentMapCache("aclCache"), // Specify the cache name
            permissionGrantingStrategy(),
            aclAuthorizationStrategy()
        );
    }

    @Bean
    public AclAuthorizationStrategy aclAuthorizationStrategy() {
        return new AclAuthorizationStrategyImpl(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Bean
    public PermissionGrantingStrategy permissionGrantingStrategy() {
        return new DefaultPermissionGrantingStrategy(new ConsoleAuditLogger());
    }

}