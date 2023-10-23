package com.pruebadevsu.cuenta_movimientos_service.services;

import com.pruebadevsu.cuenta_movimientos_service.model.dtos.AccountRequest;
import com.pruebadevsu.cuenta_movimientos_service.model.dtos.AccountResponse;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    public List<AccountResponse> findAll();

    public Optional<AccountResponse> findById(Long id);

    public AccountResponse save(AccountRequest accountRequest);

    public AccountResponse update(AccountRequest accountRequest, Long id);

    public void deleteById(Long id);
}
