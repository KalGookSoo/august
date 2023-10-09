package com.kalgookso.august.service;

import com.kalgookso.august.command.AccountCommand;
import com.kalgookso.august.entity.Account;
import com.kalgookso.august.entity.Authority;
import com.kalgookso.august.mapper.AccountMapper;
import com.kalgookso.august.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AccountService {

    /**
     * 계정 저장소
     */
    private final AccountRepository accountRepository;

    /**
     * 계정 매퍼
     */
    private final AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

    /**
     * 패스워드 인코더
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * 테스트용 계정 생성
     * @param username 계정명
     * @param password 패스워드
     * @param name     이름
     * @return 생성된 계정
     */
    public Account test(String username, String password, String name) {
        Account account = new Account(username, this.passwordEncoder.encode(password), name);
        return this.accountRepository.save(account);
    }

    /**
     * 계정을 저장합니다.
     * @param command 계정 생성 커맨드
     * @return 저장된 계정
     */
    public Account save(AccountCommand.Post command) {
        Account account = this.accountMapper.convert(command);
        account.setPassword(this.passwordEncoder.encode(command.getPassword()));
        account.addAuthority(new Authority("ROLE_USER"));
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
     * 계정을 수정합니다.
     * @param id      식별자
     * @param command 계정 수정 커맨드
     * @return 수정된 계정
     */
    public Account updateById(String id, AccountCommand.Put command) {
        Account account = this.accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));
        Account mergedAccount = this.accountMapper.convert(account, command);
        return this.accountRepository.save(mergedAccount);
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

}
