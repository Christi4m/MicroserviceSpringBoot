package com.pruebadevsu.cuenta_movimientos_service.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequest {
    private String number;

    private String type;


    private BigDecimal openingBalance;

    private boolean state;

    private Long idClient;

}
