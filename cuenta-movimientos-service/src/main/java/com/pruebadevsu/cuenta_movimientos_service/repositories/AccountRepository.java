package com.pruebadevsu.cuenta_movimientos_service.repositories;

import com.pruebadevsu.cuenta_movimientos_service.model.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
