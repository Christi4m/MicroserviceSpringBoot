package com.pruebadevsu.cuenta_movimientos_service.services;

import com.pruebadevsu.cuenta_movimientos_service.exceptions.BadRequestException;
import com.pruebadevsu.cuenta_movimientos_service.exceptions.NotFoundException;
import com.pruebadevsu.cuenta_movimientos_service.helpers.BeanUtilsHelper;
import com.pruebadevsu.cuenta_movimientos_service.model.dtos.AccountRequest;
import com.pruebadevsu.cuenta_movimientos_service.model.dtos.AccountResponse;
import com.pruebadevsu.cuenta_movimientos_service.model.dtos.ClientResponse;
import com.pruebadevsu.cuenta_movimientos_service.model.entities.Account;
import com.pruebadevsu.cuenta_movimientos_service.model.mappers.AccountMapper;
import com.pruebadevsu.cuenta_movimientos_service.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final WebClient.Builder webClientBuilder;
    @Autowired
    private BeanUtilsHelper beanUtilsHelper;

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(account -> AccountMapper.INSTANCE.accountToAccountResponse(account))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AccountResponse> findById(Long id) {
        return repository.findById(id).map(account -> AccountMapper.INSTANCE.accountToAccountResponse(account));
    }


    @Override
    @Transactional()
    public AccountResponse save(AccountRequest accountRequest) {
        var result = this.getClient(accountRequest.getIdClient());
        if(result == null) {
            throw new NotFoundException("Could not find client with id: " + accountRequest.getIdClient());

        }
        var account = AccountMapper.INSTANCE.accountRequestToAccount(accountRequest);
        account.setNameClient(result.getName());
        account.setBalance(accountRequest.getOpeningBalance());
        var res = repository.save(account);
        return AccountMapper.INSTANCE.accountToAccountResponse(res);


    }

    @Override
    @Transactional()
    public AccountResponse update(AccountRequest accountRequest, Long id) {

        Optional<Account> account = repository.findById(id);

        if (!account.isPresent()) {
             throw new NotFoundException("Could not find account with id: " + id);
        }

        if(accountRequest.getOpeningBalance() != null ){

            accountRequest.setOpeningBalance(null);

        }

        if(accountRequest.getIdClient() != null){
            throw new BadRequestException("It is not possible to modify the account client by this means.");
        }

        var accountExist = account.get();

        beanUtilsHelper.copyNonNullProperties(accountExist, accountRequest);

        var res = repository.save(accountExist);
        return AccountMapper.INSTANCE.accountToAccountResponse(res);
    }

    @Override
    @Transactional()
    public void deleteById(Long id) {
        repository.deleteById(id);
    }


    public ClientResponse getClient(Long id){
        ClientResponse result = null;
        try {
            result = this.webClientBuilder.build()
                    .get()
                    .uri("lb://cp-service/api/cp/clientes/" + id)
                    .retrieve()
                    .bodyToMono(ClientResponse.class)
                    .block();

        } catch (WebClientResponseException.NotFound e) {
            log.error("Could not find client with id: {}", id);
        } catch (WebClientException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }
}
