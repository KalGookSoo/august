package com.kalgookso.august.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts/rest")
public class AccountRestController {

    @GetMapping
    public PagedModel getAll(Pageable pageable) {
        return null;
    }

}
