package com.kalgookso.august.controller;


import com.kalgookso.august.command.AccountCommand;
import com.kalgookso.august.service.AccountService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    /**
     * 계정 목록이 담긴 뷰를 반환합니다.
     * @param pageable 페이지 정보
     * @param model    모델
     * @return 뷰
     */
    @GetMapping
    public String getAll(Pageable pageable, Model model) {
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
        return null;
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
        return null;
    }

    /**
     * 계정 정보를 수정하기 위한 뷰를 반환합니다.
     * @param id    계정 식별자
     * @param model 모델
     * @return 뷰
     */
    @GetMapping("/{id}/edit")
    public String getEdit(@PathVariable String id, Model model) {
        return null;
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
        return null;
    }

    /**
     * 계정을 삭제합니다.
     * @param id 계정 식별자
     * @return 뷰
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        return null;
    }

    /**
     * 계정 패스워드를 수정하기 위한 뷰를 반환합니다.
     * @param id    계정 식별자
     * @param model 모델
     * @return 뷰
     */
    @GetMapping("/{id}/password")
    public String getEditPassword(@PathVariable String id, Model model) {
        return null;
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
        return null;
    }

}