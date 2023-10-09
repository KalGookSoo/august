package com.kalgookso.august.controller;


import com.kalgookso.august.command.AccountCommand;
import com.kalgookso.august.entity.Account;
import com.kalgookso.august.mapper.AccountMapper;
import com.kalgookso.august.service.AccountService;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    private final AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getAll(Model model, Pageable pageable) {
        Page<Account> page = this.accountService.findAll(pageable);
        model.addAttribute("page", page);
        return "accounts/list";
    }

    @GetMapping("/new")
    public String getNew(@SuppressWarnings("unused") Model model, @ModelAttribute AccountCommand.Post command) {
        return "accounts/new";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable String id, Model model) {
        AccountCommand.Response response = this.accountService.findById(id)
                .map(this.accountMapper::convert)
                .orElseGet(() -> {
                    model.addAttribute("error", "존재하지 않는 계정입니다.");
                    return null;
                });

        model.addAttribute("account", response);
        return "accounts/view";

    }

    @PostMapping
    public String create(Model model, @ModelAttribute @Valid AccountCommand.Post command, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return this.getNew(model, command);
        }

        return this.accountService.findByUsername(command.getUsername())
                .map(account -> {
                    bindingResult.addError(new FieldError("account", "username", "계정이 이미 존재합니다."));
                    model.addAttribute("errors", bindingResult.getAllErrors());
                    return this.getNew(model, command);
                })
                .orElseGet(() -> {
                    Account account = this.accountService.save(command);
                    return "redirect:/accounts/" + account.getId();
                });

    }

    @GetMapping("/{id}/edit")
    public String getEdit(Model model, @PathVariable String id) {
        AccountCommand.Response response = this.accountService.findById(id)
                .map(this.accountMapper::convert)
                .orElseGet(() -> {
                    model.addAttribute("error", "존재하지 않는 계정입니다.");
                    return null;
                });

        model.addAttribute("account", response);
        return "accounts/edit";
    }

    @PutMapping("/{id}")
    public String update(Model model, @PathVariable String id, @ModelAttribute @Valid AccountCommand.Put command, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return this.getEdit(model, id);
        }

        Account account = this.accountService.updateById(id, command);
        return "redirect:/accounts/" + account.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        this.accountService.deleteById(id);
        return "redirect:/accounts";
    }

}
