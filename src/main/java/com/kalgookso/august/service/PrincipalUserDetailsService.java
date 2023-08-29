package com.kalgookso.august.service;

import com.kalgookso.august.entity.Account;
import com.kalgookso.august.repository.AccountRepository;
import com.kalgookso.august.security.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public PrincipalUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account foundAccount = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new UserPrincipal(foundAccount);
    }

}
