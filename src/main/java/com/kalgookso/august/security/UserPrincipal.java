package com.kalgookso.august.security;

import com.kalgookso.august.entity.account.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Spring Security가 loginProcessingUrl에 명시한 요청이 오면 낚아채서 로그인을 진행시킨다.
 * 로그인 진행이 완료가 되면 Session을 만들어준다.
 * Security ContextHolder에 세션 정보를 저장시킨다.
 * Authentication 안에 Account(AccountDetails) 정보가 있어야 한다.
 * Security Session에는 Authentication 타입의 객체가 있어야 하고 Authentication 안에는 AccountDetails(PrincipalDetails) 타입의 객체가 있어야 한다.
 */
@SuppressWarnings("unused")
public class UserPrincipal implements UserDetails, Serializable {

    /**
     * 계정
     */
    private Account account;

    /**
     * 일반 로그인할 때 생성되는 생성자
     */
    public UserPrincipal(Account account) {
        this.account = account;
    }

    /**
     * 기본 생성자입니다.
     * 현재는 아무런 동작을 하지 않습니다.
     */
    protected UserPrincipal() {
    }

    /**
     * 계정의 권한 목록을 반환하는 메서드입니다.
     * 계정에 부여된 각 권한을 SimpleGrantedAuthority 객체로 변환하여 Set 형태로 반환합니다.
     *
     * @return 계정의 권한 목록
     */
    @Override
    public Set<? extends GrantedAuthority> getAuthorities() {
        return account
                .getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toSet());
    }

    /**
     * 계정의 패스워드를 반환합니다.
     * @return 패스워드
     */
    @Override
    public String getPassword() {
        return this.account.getPassword();
    }

    /**
     * 계정명을 반환합니다.
     * @return 이름
     */
    @Override
    public String getUsername() {
        return this.account.getUsername();
    }

    /**
     * 계정이 만료되지 않았는지 여부를 반환합니다.
     * @return 계정이 만료되지 않았는지 여부
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정이 잠겨있지 않은지 여부를 반환합니다.
     * @return 계정이 잠겨있지 않은지 여부
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 계정의 패스워드가 만료되지 않았는지 여부를 반환합니다.
     * @return 계정의 패스워드가 만료되지 않았는지 여부
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 계정이 사용 가능한지 여부를 반환합니다.
     * @return 계정이 사용 가능한지 여부
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
