package com.pruebadevsu.cuenta_movimientos_service.model.mappers;

import com.pruebadevsu.cuenta_movimientos_service.model.dtos.TransactionRequest;
import com.pruebadevsu.cuenta_movimientos_service.model.dtos.TransactionResponse;
import com.pruebadevsu.cuenta_movimientos_service.model.entities.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction transactionRequestToTransaction(TransactionRequest TransactionRequest);
    Transaction transactionResponseToTransaction(TransactionResponse TransactionResponse);

    TransactionResponse transactionToTransactionResponse(Transaction Transaction);
}
