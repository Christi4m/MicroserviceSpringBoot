package com.pruebadevsu.cuenta_movimientos_service.model.mappers;

import com.pruebadevsu.cuenta_movimientos_service.model.dtos.AccountRequest;
import com.pruebadevsu.cuenta_movimientos_service.model.dtos.AccountResponse;
import com.pruebadevsu.cuenta_movimientos_service.model.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account accountRequestToAccount(AccountRequest accountRequest);
    Account accountResponseToAccount(AccountResponse accountResponse);

    AccountResponse accountToAccountResponse(Account account);
}


