package com.kalgookso.august.model.assembler;

import com.kalgookso.august.controller.AccountController;
import com.kalgookso.august.entity.Account;
import com.kalgookso.august.mapper.AccountMapper;
import com.kalgookso.august.model.AccountModel;
import org.mapstruct.factory.Mappers;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * 계정 모델 어셈블러
 */
@Component
public class AccountModelAssembler extends RepresentationModelAssemblerSupport<Account, AccountModel> {

    /**
     * 계정 매퍼
     */
    private final AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

    /**
     * 계정 모델 어셈블러 생성자
     */
    public AccountModelAssembler() {
        super(AccountController.class, AccountModel.class);
    }

    /**
     * 엔티티를 모델로 변환합니다.
     * @param account 엔티티
     * @return 모델
     */
    @Nonnull
    @Override
    public AccountModel toModel(@Nonnull Account account) {
        AccountModel model = this.accountMapper.convert(account);
        model.add(linkTo(AccountController.class).slash(account.getId()).withSelfRel());
        return model;
    }

}