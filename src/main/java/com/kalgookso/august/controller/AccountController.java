package com.kalgookso.august.controller;


import com.kalgookso.august.command.AccountCommand;
import com.kalgookso.august.entity.Account;
import com.kalgookso.august.model.AccountModel;
import com.kalgookso.august.model.assembler.AccountModelAssembler;
import com.kalgookso.august.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.function.Supplier;

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
     * 계정 모델 어셈블러
     */
    private final AccountModelAssembler accountModelAssembler;

    /**
     * 페이지 리소스 어셈블러
     */
    private final PagedResourcesAssembler<Account> pagedResourcesAssembler;

    /**
     * 계정 컨트롤러 생성자
     *
     * @param accountService          계정 서비스
     * @param accountModelAssembler   계정 모델 어셈블러
     * @param pagedResourcesAssembler 페이지 리소스 어셈블러
     */
    public AccountController(AccountService accountService, AccountModelAssembler accountModelAssembler, PagedResourcesAssembler<Account> pagedResourcesAssembler) {
        this.accountService = accountService;
        this.accountModelAssembler = accountModelAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    /**
     * 계정 목록이 담긴 뷰를 반환합니다.
     * @param pageable 페이지 정보
     * @param model    모델
     * @return 뷰
     */
    @GetMapping
    public String getAll(Pageable pageable, Model model) {
        Page<Account> page = this.accountService.findAll(pageable);
        PagedModel<AccountModel> pagedModel = pagedResourcesAssembler.toModel(page, this.accountModelAssembler);
        model.addAttribute("pagedModel", pagedModel);
        return "accounts/list";
    }

    /**
     * 계정 생성을 위한 뷰를 반환합니다.
     * @param command 계정 커맨드
     * @return 뷰
     */
    @GetMapping("/new")
    public String getNew(@ModelAttribute("command") AccountCommand.Post command) {
        return "accounts/new";
    }

    /**
     * 계정 정보가 담긴 뷰를 반환합니다.
     * @param id    계정 식별자
     * @param model 모델
     * @return 뷰
     */
    @GetMapping("/{id}")
    public String getOne(@PathVariable String id, Model model) {
        return this.accountService.findById(id)
                .map(account -> {
                    model.addAttribute("account", account);
                    return "accounts/view";
                })
                .orElseGet(this.noContentSupplier(model, "존재하지 않는 계정입니다."));
    }

    /**
     * 계정을 생성합니다.
     * @param command       계정 커맨드
     * @param bindingResult 검증 결과
     * @param model         모델
     * @return 뷰
     */
    @PostMapping
    public String create(@ModelAttribute("command") @Valid AccountCommand.Post command, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return this.getNew(command);
        }

        return this.accountService.findByUsername(command.getUsername())
                .map(account -> {
                    bindingResult.addError(new FieldError("account", "username", "계정이 이미 존재합니다."));
                    model.addAttribute("errors", bindingResult.getAllErrors());
                    return this.getNew(command);
                })
                .orElseGet(() -> "redirect:/accounts/" + this.accountService.create(command).getId());
    }

    /**
     * 계정 정보를 수정하기 위한 뷰를 반환합니다.
     * @param id    계정 식별자
     * @param model 모델
     * @return 뷰
     */
    @GetMapping("/{id}/edit")
    public String getEdit(@PathVariable String id, Model model) {
        return this.accountService.findById(id)
                .map(account -> {
                    model.addAttribute("command", account);
                    return "accounts/edit";
                })
                .orElseGet(this.noContentSupplier(model, "존재하지 않는 계정입니다."));
    }

    /**
     * 계정 정보를 수정합니다.
     * @param id            계정 식별자
     * @param command       계정 커맨드
     * @param bindingResult 검증 결과
     * @param model         모델
     * @return 뷰
     */
    @PutMapping("/{id}")
    public String update(Model model, @PathVariable String id, @ModelAttribute("command") @Valid AccountCommand.Put command, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "accounts/edit";
        }

        return this.accountService.findById(id)
                .map(account -> this.accountService.convert(account, command))
                .map(this.accountService::save)
                .map(account -> "redirect:/accounts/" + account.getId())
                .orElseGet(this.noContentSupplier(model, "존재하지 않는 계정입니다."));

    }

    /**
     * 계정을 삭제합니다.
     * @param id 계정 식별자
     * @return 뷰
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        this.accountService.deleteById(id);
        return "redirect:/accounts";
    }

    /**
     * 계정 패스워드를 수정하기 위한 뷰를 반환합니다.
     * @param id    계정 식별자
     * @param model 모델
     * @return 뷰
     */
    @GetMapping("/{id}/password")
    public String getEditPassword(@PathVariable String id, Model model) {
        return this.accountService.findById(id)
                .map(account -> {
                    model.addAttribute("account", account);
                    return "accounts/editPassword";
                })
                .orElseGet(this.noContentSupplier(model, "존재하지 않는 계정입니다."));
    }

    /**
     * 계정 패스워드를 수정합니다.
     * @param id            계정 식별자
     * @param command       계정 커맨드
     * @param bindingResult 검증 결과
     * @param model         모델
     * @return 뷰
     */
    @PutMapping("/{id}/password")
    public String updatePassword(@PathVariable String id, @ModelAttribute("command") @Valid AccountCommand.Password command, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "accounts/editPassword";
        }

        return this.accountService.findById(id).map(account -> {
            if (this.accountService.isMatch(command.getOriginPassword(), account.getPassword())) {
                account.changePassword(this.accountService.encode(command.getNewPassword()));
                this.accountService.save(account);
            } else {
                bindingResult.addError(new FieldError("account", "originPassword", "기존 비밀번호가 일치하지 않습니다."));
                model.addAttribute("errors", bindingResult.getAllErrors());
            }
            return "accounts/editPassword";

        }).orElseGet(this.noContentSupplier(model, "존재하지 않는 계정입니다."));

    }

    /**
     * 계정이 존재하지 않을 때 반환할 뷰를 반환합니다.
     * @param model          모델
     * @param attributeValue 에러 메시지
     * @return 뷰
     */
    private Supplier<String> noContentSupplier(Model model, @SuppressWarnings("SameParameterValue") String attributeValue) {
        return () -> {
            model.addAttribute("errors", attributeValue);
            return this.getAll(Pageable.unpaged(), model);
        };
    }

}