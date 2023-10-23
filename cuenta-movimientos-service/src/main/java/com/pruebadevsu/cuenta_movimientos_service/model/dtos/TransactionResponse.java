package com.pruebadevsu.cuenta_movimientos_service.model.dtos;


import com.pruebadevsu.cuenta_movimientos_service.model.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {

    private Long id;

    private String type;

    private Date date;

    private BigDecimal value;

}
