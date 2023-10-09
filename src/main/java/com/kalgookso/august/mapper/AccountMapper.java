package com.kalgookso.august.mapper;

import com.kalgookso.august.command.AccountCommand;
import com.kalgookso.august.entity.Account;
import org.mapstruct.*;

import java.util.Map;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = Converter.class)
public interface AccountMapper {

    /**
     * 계정 생성 커맨드를 엔티티로 변환합니다.
     * @param command 계정 생성 커맨드
     * @return 엔티티
     */
    Account convert(AccountCommand.Post command);

    /**
     * 응답 커맨드로 변환합니다.
     * @param account 엔티티
     * @return 응답 커맨드
     */
    AccountCommand.Response convert(Account account);

    /**
     * 엔티티를 수정합니다.
     * @param originEntity 기존 엔티티
     * @param newEntity    변경된 엔티티
     * @return 엔티티
     */
    @Mapping(target = "id", ignore = true)
    Account convert(@MappingTarget Account originEntity, AccountCommand.Put newEntity);

    /**
     * 엔티티를 일부 수정합니다.
     * @param account 기존 엔티티
     * @param payload 요청 본문
     * @return 엔티티
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    Account convert(@MappingTarget Account account, Map<String, Object> payload);

}
