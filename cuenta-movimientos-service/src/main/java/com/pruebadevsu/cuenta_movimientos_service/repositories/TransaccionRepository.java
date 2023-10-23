package com.pruebadevsu.cuenta_movimientos_service.repositories;

import com.pruebadevsu.cuenta_movimientos_service.model.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaction, Long> {
}
