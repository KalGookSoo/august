package com.kalgookso.august.service;

import com.kalgookso.august.entity.Account;
import com.kalgookso.august.repository.AccountRepository;
import com.kalgookso.august.security.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Spring Security가 loginProcessingUrl에 명시한 요청이 오면 낚아채서 로그인을 진행시킨다.
 */
@Service
public class PrincipalUserDetailsService implements UserDetailsService {

    /**
     * 계정 저장소
     */
    private final AccountRepository accountRepository;

    /**
     * 생성자
     * @param accountRepository 계정 저장소
     */
    public PrincipalUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * 로그인 시도시 호출되는 메소드
     * @param username 계정명
     * @return 계정
     * @throws UsernameNotFoundException 계정을 찾을 수 없을 때 발생
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /*
        * 계정명으로 계정을 조회합니다.
        * 계정명이 존재하지 않으면 UsernameNotFoundException을 발생시킵니다.
        * 계정명이 존재하면 UserPrincipal 객체를 생성하여 반환합니다.
        * UserPrincipal 객체는 Spring Security가 로그인을 진행할 때 사용합니다.
        * Spring Security는 UserPrincipal 객체를 Authentication 객체에 담아서 SecurityContextHolder에 저장합니다.
        * Authentication 객체는 Security Session에 저장됩니다.
        * Authentication 객체 안에는 UserPrincipal 객체가 있어야 합니다.
        * */
        Account foundAccount = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new UserPrincipal(foundAccount);
    }

}
