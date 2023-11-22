package com.kalgookso.august.controller;


import com.kalgookso.august.command.CreateAccountCommand;
import com.kalgookso.august.command.UpdateAccountCommand;
import com.kalgookso.august.command.UpdateAccountPasswordCommand;
import com.kalgookso.august.entity.Account;
import com.kalgookso.august.mapper.AccountMapper;
import com.kalgookso.august.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * 계정 컨트롤러
 */
@Controller
@RequestMapping("/accounts")
public class AccountController {

    /**
     * 계정 서비스
     */
    private final AccountService accountService;

    /**
     * 계정 컨트롤러 생성자
     *
     * @param accountService 계정 서비스
     */
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getAll(Pageable pageable, Model model) {
        Page<Account> accounts = accountService.findAll(pageable);
        model.addAttribute("accounts", accounts);
        return "accounts/list";
    }

    @GetMapping("/new")
    public String getNew(@ModelAttribute("command") CreateAccountCommand command) {
        return "accounts/new";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable String id, Model model) {
        Optional<Account> account = accountService.findById(id);
        model.addAttribute("account", account.orElseThrow());
        return "accounts/detail";
    }

    @PostMapping
    public String create(@ModelAttribute("command") @Valid CreateAccountCommand command, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "accounts/new";
        }
        Account account = AccountMapper.INSTANCE.toEntity(command);
        Account savedAccount = accountService.save(account);
        return "redirect:/accounts/" + savedAccount.getId();
    }

    @GetMapping("/{id}/edit")
    public String getEdit(@PathVariable String id, Model model) {
        Optional<Account> account = accountService.findById(id);
        model.addAttribute("account", account.orElseThrow());
        return "accounts/edit";
    }

    @PutMapping("/{id}")
    public String update(Model model, @PathVariable String id, @ModelAttribute("command") @Valid UpdateAccountCommand command, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "accounts/edit";
        }
        Optional<Account> foundAccount = accountService.findById(id);
        if (foundAccount.isEmpty()) {
            throw new IllegalArgumentException("계정을 찾을 수 없습니다.");
        }
        Account account = AccountMapper.INSTANCE.toEntity(foundAccount.get(), command);
        Account savedAccount = accountService.save(account);
        return "redirect:/accounts/" + savedAccount.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        accountService.deleteById(id);
        return "redirect:/accounts";
    }

    @GetMapping("/{id}/password")
    public String getEditPassword(@PathVariable String id, Model model) {
        Optional<Account> account = accountService.findById(id);
        model.addAttribute("account", account.orElseThrow());
        return "accounts/password";
    }

    @PutMapping("/{id}/password")
    public String updatePassword(@PathVariable String id, @ModelAttribute("command") @Valid UpdateAccountPasswordCommand command, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "accounts/password";
        }
        Optional<Account> foundAccount = accountService.findById(id);
        if (foundAccount.isEmpty()) {
            throw new IllegalArgumentException("계정을 찾을 수 없습니다.");
        }
        Account account = foundAccount.get();
        account.setPassword(command.getNewPassword());
        Account savedAccount = accountService.save(account);
        return "redirect:/accounts/" + account.getId();
    }

}