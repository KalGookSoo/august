package com.kalgookso.august.controller;

import com.kalgookso.august.command.AccountCommand;
import com.kalgookso.august.entity.Account;
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
 * 계정 컨트롤러
 */
@Controller
public class SignController {

    /**
     * 계정 서비스
     */
    private final AccountService accountService;

    public SignController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/sign-in")
    public String signIn() {
        return "sign-in";
    }

    @GetMapping("/sign-up")
    public String signUp(@ModelAttribute("command") AccountCommand.Post command) {
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute("command") @Valid AccountCommand.Post command, BindingResult result) {

        if (result.hasErrors()) {
            return "sign-up";
        }

        Optional<Account> account = this.accountService.findByUsername(command.getUsername());

        if (account.isPresent()) {
            result.addError(new FieldError("command", "username", "계정이 이미 존재합니다."));
            return "sign-up";
        }

        @SuppressWarnings("unused")
        Account savedAccount = this.accountService.create(command);

        return "sign-in";

    }

}