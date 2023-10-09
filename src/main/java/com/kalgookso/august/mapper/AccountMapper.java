package com.kalgookso.august.mapper;

import com.kalgookso.august.command.AccountCommand;
import com.kalgookso.august.entity.Account;
import org.mapstruct.*;

import java.util.Map;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    Account convert(AccountCommand.Post command);

    AccountCommand.Response convert(Account account);

    @Mapping(target = "id", ignore = true)
    Account convert(@MappingTarget Account originEntity, AccountCommand.Put newEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    Account convert(@MappingTarget Account account, Map<String, Object> payload);

}
