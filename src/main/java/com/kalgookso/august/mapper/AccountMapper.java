package com.kalgookso.august.mapper;

import com.kalgookso.august.command.CreateAccountCommand;
import com.kalgookso.august.command.UpdateAccountCommand;
import com.kalgookso.august.entity.Account;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/**
 * 계정 매퍼 인터페이스입니다.
 * 이 인터페이스는 계정 생성 명령과 계정 업데이트 명령을 계정 엔티티로 변환하는 메서드를 정의합니다.
 * 이 인터페이스는 MapStruct 라이브러리를 사용하여 구현체를 자동으로 생성합니다.
 * 또한, AccountMapperDecorator 클래스를 사용하여 추가적인 변환 로직을 제공합니다.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
@DecoratedWith(AccountMapperDecorator.class)
public interface AccountMapper {

    /**
     * 계정 매퍼 인스턴스입니다.
     * 이 인스턴스는 MapStruct 라이브러리에 의해 자동으로 생성됩니다.
     */
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    /**
     * 계정 생성 명령을 계정 엔티티로 변환하는 메서드입니다.
     * @param command 계정 생성 명령
     * @return 변환된 계정 엔티티
     */
    Account toEntity(CreateAccountCommand command);

    /**
     * 계정 업데이트 명령을 기존 계정 엔티티에 적용하여 새 계정 엔티티를 생성하는 메서드입니다.
     * 이 메서드는 id, username, password 필드를 무시합니다.
     * @param originEntity 기존 계정 엔티티
     * @param command 계정 업데이트 명령
     * @return 변환된 계정 엔티티
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "password", ignore = true)
    Account toEntity(@MappingTarget Account originEntity, UpdateAccountCommand command);

}