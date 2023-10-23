package com.pruebadevsu.cuenta_movimientos_service.services;

import com.pruebadevsu.cuenta_movimientos_service.exceptions.BadRequestException;
import com.pruebadevsu.cuenta_movimientos_service.exceptions.NotFoundException;
import com.pruebadevsu.cuenta_movimientos_service.model.dtos.TransactionRequest;
import com.pruebadevsu.cuenta_movimientos_service.model.dtos.TransactionResponse;
import com.pruebadevsu.cuenta_movimientos_service.model.mappers.TransactionMapper;
import com.pruebadevsu.cuenta_movimientos_service.repositories.AccountRepository;
import com.pruebadevsu.cuenta_movimientos_service.repositories.TransaccionRepository;
import com.pruebadevsu.cuenta_movimientos_service.utils.enums.TransactionTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService{

    private final TransaccionRepository repository;
    private final AccountRepository accountRepository;


    @Override
    @Transactional(readOnly = true)
    public List<TransactionResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(transaction -> TransactionMapper.INSTANCE.transactionToTransactionResponse(transaction))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TransactionResponse> findById(Long id) {
        return repository.findById(id).map(transaction -> TransactionMapper.INSTANCE.transactionToTransactionResponse(transaction));
    }

    @Override
    @Transactional()
    public TransactionResponse save(TransactionRequest transactionRequest) {

        var account = accountRepository.findById(transactionRequest.getAccountId());

        if(!account.isPresent()){
            throw new NotFoundException("Could not find account with id: " + transactionRequest.getAccountId());
        }
        if(!TransactionTypeEnum.matches(transactionRequest.getType())){
            throw new BadRequestException("transaction type invalid, options valid: " + TransactionTypeEnum.getDisplayNames());
        }


        var transaction =  TransactionMapper.INSTANCE.transactionRequestToTransaction(transactionRequest);
        log.info("transaction {} ", transaction);
        transaction.setAccount(account.get());
        var res = repository.save(transaction);

        if(res != null){
            var accountBalance = account.get().getBalance();
            var valueTransaction = res.getValue();
            if(TransactionTypeEnum.equalsIgnoreCase(transactionRequest.getType(), TransactionTypeEnum.DEPOSIT)){
                account.get().setBalance(accountBalance.add(valueTransaction));
            } else if (TransactionTypeEnum.equalsIgnoreCase(transactionRequest.getType(), TransactionTypeEnum.WITHDRAWAL)) {
                account.get().setBalance(accountBalance.subtract(valueTransaction));
            }

            accountRepository.save(account.get());
        }
        return TransactionMapper.INSTANCE.transactionToTransactionResponse(res);
    }


    @Override
    @Transactional()
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
