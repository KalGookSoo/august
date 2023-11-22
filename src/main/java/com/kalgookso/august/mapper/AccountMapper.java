package com.kalgookso.august.mapper;

import com.kalgookso.august.command.CreateAccountCommand;
import com.kalgookso.august.command.UpdateAccountCommand;
import com.kalgookso.august.entity.Account;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
@DecoratedWith(AccountMapperDecorator.class)
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    /**
     * 계정 생성 커맨드를 엔티티로 변환합니다.
     * @param command 계정 생성 커맨드
     * @return 엔티티
     */
    Account toEntity(CreateAccountCommand command);

    /**
     * 엔티티를 수정합니다.
     * @param originEntity 기존 엔티티
     * @param command      변경된 엔티티
     * @return 엔티티
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "password", ignore = true)
    Account toEntity(@MappingTarget Account originEntity, UpdateAccountCommand command);

}
