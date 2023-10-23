package com.pruebadevsu.cuenta_movimientos_service.model.dtos;

import com.pruebadevsu.cuenta_movimientos_service.model.entities.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountResponse {

    private Long id;

    private String number;

    private String type;

    private BigDecimal balance;

    private BigDecimal openingBalance;

    private boolean state;

    private String nameClient;

    private Long idClient;

    private List<Transaction> transactions;
}
