package com.kalgookso.august.security;

import com.kalgookso.august.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Spring Security가 loginProcessingUrl에 명시한 요청이 오면 낚아채서 로그인을 진행시킨다.
 * 로그인 진행이 완료가 되면 Session을 만들어준다.
 * Security ContextHolder에 세션 정보를 저장시킨다.
 * Authentication 안에 Account(AccountDetails) 정보가 있어야 한다.
 * Security Session에는 Authentication 타입의 객체가 있어야 하고 Authentication 안에는 AccountDetails(PrincipalDetails) 타입의 객체가 있어야 한다.
 */
@SuppressWarnings("unused")
public class UserPrincipal implements UserDetails, Serializable {

    private Account account;

    /** 일반 로그인할 때 생성되는 생성자 */
    public UserPrincipal(Account account) {
        this.account = account;
    }

    protected UserPrincipal() {
    }

    @Override
    public Set<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        this.account.getAuthorities().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.account.getPassword();
    }

    @Override
    public String getUsername() {
        return this.account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
