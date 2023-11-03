package com.kalgookso.august.service;

import com.kalgookso.august.command.AccountCommand;
import com.kalgookso.august.entity.Account;
import com.kalgookso.august.entity.Authority;
import com.kalgookso.august.mapper.AccountMapper;
import com.kalgookso.august.repository.AccountRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 계정 서비스
 */
@Service
public class DefaultAccountService implements AccountService {

    /**
     * 계정 저장소
     */
    private final AccountRepository accountRepository;

    /**
     * 패스워드 인코더
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * 계정 매퍼
     */
    private final AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

    /**
     * 계정 서비스 생성자
     * @param accountRepository 계정 저장소
     * @param passwordEncoder   패스워드 인코더
     */
    public DefaultAccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 계정을 저장합니다.
     * @param command 계정 생성 커맨드
     * @return 저장된 계정
     */
    public Account create(AccountCommand.Post command) {
        Account account = this.accountMapper.convert(command);
        account.changePassword(this.encode(command.getPassword()));
        account.addAuthority(new Authority("ROLE_USER"));
        return this.accountRepository.save(account);
    }

    /**
     * 계정을 저장합니다.
     * @param account 계정
     * @return 저장된 계정
     */
    public Account save(Account account) {
        return this.accountRepository.save(account);
    }

    /**
     * 계정명에 해당하는 계정을 반환합니다.
     * @param username 계정명
     * @return 계정
     */
    public Optional<Account> findByUsername(String username) {
        return this.accountRepository.findByUsername(username);
    }

    /**
     * 식별자에 해당하는 계정을 반환합니다.
     * @param id 식별자
     * @return 계정
     */
    public Optional<Account> findById(String id) {
        return this.accountRepository.findById(id);
    }

    /**
     * 계정 전체를 조회합니다.
     * @param pageable 페이징 정보
     * @return 계정 목록
     */
    public Page<Account> findAll(Pageable pageable) {
        return this.accountRepository.findAll(pageable);
    }

    /**
     * 계정을 삭제합니다.
     * @param id 식별자
     */
    public void deleteById(String id) {
        this.accountRepository.deleteById(id);
    }

    /**
     * 패스워드가 일치하는지 확인합니다.
     * @param rawPassword     패스워드
     * @param encodedPassword 인코딩된 패스워드
     * @return 일치 여부
     */
    public boolean isMatch(CharSequence rawPassword, String encodedPassword) {
        return this.passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 패스워드를 인코딩합니다.
     * @param rawPassword 패스워드
     * @return 인코딩된 패스워드
     */
    public String encode(CharSequence rawPassword) {
        return this.passwordEncoder.encode(rawPassword);
    }

    /**
     * 계정에 커맨드 내용을 병합합니다.
     * @param account 계정
     * @param command 계정 커맨드
     * @return 계정
     */
    public Account convert(Account account, AccountCommand.Put command) {
        return this.accountMapper.convert(account, command);
    }

}