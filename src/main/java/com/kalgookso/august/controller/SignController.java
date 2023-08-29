package com.kalgookso.august.controller;

import com.kalgookso.august.command.AccountCommand;
import com.kalgookso.august.entity.Account;
import com.kalgookso.august.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SignController {

    private final AccountService accountService;

    @GetMapping("/sign-in")
    public String signIn() {
        this.accountService.test("tester", "1234", "테스터");
        return "sign-in";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(Model model, @ModelAttribute("command") AccountCommand command, BindingResult result) {

        if (result.hasErrors()) {
            return this.signUp();
        }

        Optional<Account> byUsername = this.accountService.findByUsername(command.getUsername());

        if (byUsername.isEmpty()) {
            result.addError(new FieldError("command", "username", "계정이 이미 존재합니다."));
            model.addAttribute("errors", result.getAllErrors());
            return this.signUp();
        }

        this.accountService.save(command);

        return "sign-in";

    }
}
