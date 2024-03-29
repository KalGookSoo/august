package com.kalgookso.august.cms.controller;

import com.kalgookso.august.cms.command.AccountCreateCommand;
import com.kalgookso.august.cms.command.AccountPasswordCommand;
import com.kalgookso.august.cms.command.AccountUpdateCommand;
import com.kalgookso.august.cms.entity.Account;
import com.kalgookso.august.cms.exception.UsernameAlreadyExistsException;
import com.kalgookso.august.cms.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * 계정 컨트롤러 클래스입니다.
 * 이 클래스는 AccountService를 사용하여 계정 관련 작업을 수행합니다.
 */
@Controller
@RequestMapping("/accounts")
public class AccountController {

    /**
     * 계정 서비스
     */
    private final AccountService accountService;

    /**
     * AccountController 생성자입니다.
     * @param accountService 계정 서비스
     */
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 모든 계정을 페이지로 반환하는 메서드입니다.
     * @param pageable 페이지 정보
     * @param model 모델
     * @return 계정 목록 페이지
     */
    @GetMapping
    public String getAll(@PageableDefault Pageable pageable, Model model) {
        Page<Account> page = accountService.findAll(pageable);
        model.addAttribute("page", page);
        return "accounts/list";
    }

    /**
     * 특정 계정의 상세 정보 페이지를 반환하는 메서드입니다.
     * @param id 계정 ID
     * @param model 모델
     * @return 계정 상세 정보 페이지
     */
    @GetMapping("/{id}")
    public String getOne(@PathVariable String id, Model model) {
        Optional<Account> account = accountService.findById(id);
        model.addAttribute("account", account.orElseThrow());
        return "accounts/view";
    }

    /**
     * 새 계정 생성 페이지를 반환하는 메서드입니다.
     * @param command 계정 생성 명령
     * @return 새 계정 생성 페이지
     */
    @GetMapping("/new")
    public String getNew(@ModelAttribute("command") AccountCreateCommand command) {
        return "accounts/new";
    }

    /**
     * 새 계정을 생성하는 메서드입니다.
     * @param command 계정 생성 명령
     * @param bindingResult 바인딩 결과
     * @param model 모델
     * @return 생성된 계정의 상세 정보 페이지
     */
    @PostMapping
    public String create(@ModelAttribute("command") @Valid AccountCreateCommand command, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "accounts/new";
        }
        Account account = Account.create(command.getUsername(), command.getPassword(), command.getName(), command.getEmail(), command.getContactNumber());
        try {
            Account savedAccount = accountService.create(account);
            return "redirect:/accounts/" + savedAccount.getId() + "/edit";
        } catch (UsernameAlreadyExistsException e) {
            bindingResult.addError(new FieldError("command", "username", "계정이 이미 존재합니다."));
            return "accounts/new";
        }
    }

    /**
     * 특정 계정의 수정 페이지를 반환하는 메서드입니다.
     * @param id 계정 ID
     * @param model 모델
     * @return 계정 수정 페이지
     */
    @GetMapping("/{id}/edit")
    public String getEdit(@PathVariable String id, Model model) {
        Optional<Account> foundAccount = accountService.findById(id);
        if (foundAccount.isEmpty()) {
            throw new NoSuchElementException("계정을 찾을 수 없습니다.");
        }
        Account account = foundAccount.get();
        model.addAttribute("account", account);
        AccountUpdateCommand command = new AccountUpdateCommand();
        command.setName(account.getName());
        command.setEmailId(account.getEmail().getId());
        command.setEmailDomain(account.getEmail().getDomain());
        command.setFirstContactNumber(account.getContactNumber().getFirst());
        command.setMiddleContactNumber(account.getContactNumber().getMiddle());
        command.setLastContactNumber(account.getContactNumber().getLast());
        model.addAttribute("command", command);
        return "accounts/edit";
    }

    /**
     * 특정 계정을 수정하는 메서드입니다.
     * @param id 계정 ID
     * @param command 계정 수정 명령
     * @param bindingResult 바인딩 결과
     * @param model 모델
     * @return 수정된 계정의 상세 정보 페이지
     */
    @PutMapping("/{id}")
    public String update(@PathVariable String id, @ModelAttribute("command") @Valid AccountUpdateCommand command, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "accounts/edit";
        }
        Account updatedAccount = accountService.update(id, command);
        model.addAttribute("account", updatedAccount);
        return "redirect:/accounts/" + id;
    }

    /**
     * 특정 계정을 삭제하는 메서드입니다.
     * @param id 계정 ID
     * @return 계정 목록 페이지
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        accountService.deleteById(id);
        return "redirect:/accounts";
    }

    /**
     * 특정 계정의 비밀번호 수정 페이지를 반환하는 메서드입니다.
     * @param id 계정 ID
     * @param model 모델
     * @return 계정 비밀번호 수정 페이지
     */
    @GetMapping("/{id}/password")
    public String getEditPassword(@PathVariable String id, Model model) {
        Optional<Account> account = accountService.findById(id);
        model.addAttribute("account", account.orElseThrow());
        return "accounts/edit-password";
    }

    /**
     * 특정 계정의 비밀번호를 수정하는 메서드입니다.
     * @param id 계정 ID
     * @param command 계정 비밀번호 수정 명령
     * @param bindingResult 바인딩 결과
     * @param model 모델
     * @return 계정 상세 정보 페이지
     */
    @PutMapping("/{id}/password")
    public String updatePassword(@PathVariable String id, @ModelAttribute("command") @Valid AccountPasswordCommand command, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "accounts/edit-password";
        }
        accountService.updatePassword(id, command.getNewPassword());
        return "redirect:/accounts/" + id;
    }

}