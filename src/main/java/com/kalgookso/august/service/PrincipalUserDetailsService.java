package com.kalgookso.august.service;

import com.kalgookso.august.entity.Account;
import com.kalgookso.august.repository.AccountQueryRepository;
import com.kalgookso.august.security.UserPrincipal;
import com.kalgookso.august.specification.AccountSpecification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 사용자 상세 정보 서비스 클래스입니다.
 * 이 클래스는 UserDetailsService 인터페이스를 구현하며, AccountRepository를 사용하여 사용자 상세 정보를 제공합니다.
 */
@Service
@Transactional
public class PrincipalUserDetailsService implements UserDetailsService {

    private final AccountQueryRepository accountQueryRepository;

    public PrincipalUserDetailsService(AccountQueryRepository accountQueryRepository) {
        this.accountQueryRepository = accountQueryRepository;
    }

    /**
     * 로그인 시도시 호출되는 메소드입니다.
     * 사용자 이름으로 계정을 찾아 UserPrincipal 객체를 반환합니다.
     * 계정을 찾을 수 없는 경우 UsernameNotFoundException을 발생시킵니다.
     * @param username 계정명
     * @return 계정
     * @throws UsernameNotFoundException 계정을 찾을 수 없을 때 발생
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account foundAccount = accountQueryRepository.findOne(AccountSpecification.usernameEquals(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new UserPrincipal(foundAccount);
    }

}