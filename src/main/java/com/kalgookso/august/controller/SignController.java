package com.kalgookso.august.controller;

import com.kalgookso.august.command.CreateAccountCommand;
import com.kalgookso.august.entity.Account;
import com.kalgookso.august.mapper.AccountMapper;
import com.kalgookso.august.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

/**
 * 로그인 및 회원가입 컨트롤러 클래스입니다.
 * 이 클래스는 AccountService를 사용하여 계정 관련 작업을 수행합니다.
 */
@Controller
public class SignController {

    private final AccountService accountService;  // 계정 서비스

    /**
     * SignController 생성자입니다.
     * @param accountService 계정 서비스
     */
    public SignController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 로그인 페이지를 반환하는 메서드입니다.
     * @return 로그인 페이지
     */
    @GetMapping("/sign-in")
    public String signIn() {
        return "sign-in";
    }

    /**
     * 회원가입 페이지를 반환하는 메서드입니다.
     * @param command 계정 생성 명령
     * @return 회원가입 페이지
     */
    @GetMapping("/sign-up")
    public String signUp(@ModelAttribute("command") CreateAccountCommand command) {
        return "sign-up";
    }

    /**
     * 회원가입 요청을 처리하는 메서드입니다.
     * 유효하지 않은 명령이나 이미 존재하는 계정명이 있는 경우, 에러를 반환하고 회원가입 페이지로 리다이렉트합니다.
     * 그렇지 않은 경우, 계정을 생성하고 로그인 페이지로 리다이렉트합니다.
     * @param command 계정 생성 명령
     * @param result 바인딩 결과
     * @return 리다이렉트 페이지
     */
    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute("command") @Valid CreateAccountCommand command, BindingResult result) {

        if (result.hasErrors()) {
            return "sign-up";
        }

        final Optional<Account> foundAccount = this.accountService.findByUsername(command.getUsername());

        if (foundAccount.isPresent()) {
            result.addError(new FieldError("command", "username", "계정이 이미 존재합니다."));
            return "sign-up";
        }

        final Account account = AccountMapper.INSTANCE.toEntity(command);

        @SuppressWarnings("unused")
        final Account savedAccount = accountService.create(account);

        return "sign-in";

    }

}