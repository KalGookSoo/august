package com.kalgookso.august.configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->  authorizationManagerRequestMatcherRegistry
                        .requestMatchers(new AntPathRequestMatcher("/accounts/**")).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/managers/**")).hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/admins/**")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/sign-in")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/sign-up")).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/sign-in")
                        .loginProcessingUrl("/sign-in")
                        .successHandler((request, response, authentication) -> response.sendRedirect("/"))
                        .usernameParameter("username")

                );

        return http.build();
    }

    /**
     * 정적 리소스에 대해서는 시큐리티 필터를 적용하지 않는다.
     * {@link org.springframework.boot.autoconfigure.security.StaticResourceLocation}
     *
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

}
