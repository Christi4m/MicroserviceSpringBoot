package com.pruebadevsu.cuenta_movimientos_service.services;

import com.pruebadevsu.cuenta_movimientos_service.model.dtos.TransactionRequest;
import com.pruebadevsu.cuenta_movimientos_service.model.dtos.TransactionResponse;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    public List<TransactionResponse> findAll();

    public Optional<TransactionResponse> findById(Long id);

    public TransactionResponse save(TransactionRequest transactionRequest);


    public void deleteById(Long id);

}
