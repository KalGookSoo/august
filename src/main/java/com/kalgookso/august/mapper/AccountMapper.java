package com.kalgookso.august.mapper;

import com.kalgookso.august.command.AccountCommand;
import com.kalgookso.august.entity.Account;
import com.kalgookso.august.model.AccountModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    /**
     * 계정 생성 커맨드를 엔티티로 변환합니다.
     * @param command 계정 생성 커맨드
     * @return 엔티티
     */
    Account convert(AccountCommand.Post command);

    /**
     * 엔티티를 수정합니다.
     * @param originEntity 기존 엔티티
     * @param command      변경된 엔티티
     * @return 엔티티
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "password", ignore = true)
    Account convert(@MappingTarget Account originEntity, AccountCommand.Put command);

    AccountModel convert(Account account);

}
