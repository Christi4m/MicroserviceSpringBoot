package com.pruebadevsu.cuenta_movimientos_service.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;



@Entity
@Table(name = "account_ms")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"transactions"})
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false)
    private String number;

    @Column(length = 50, nullable = false)
    private String type;

    @Column(precision = 19, scale = 4, nullable = false)
    private BigDecimal balance;

    @Column(precision = 19, scale = 4, nullable = false, name = "opening_balance")
    private BigDecimal openingBalance;

    @Column(nullable = false)
    private boolean state;

    @Column(length = 50, name = "id_client")
    private String idClient;

    @Column(length = 50, nullable = false, name = "name_client")
    private String nameClient;

    @JsonIgnoreProperties(value={"account", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;
}
