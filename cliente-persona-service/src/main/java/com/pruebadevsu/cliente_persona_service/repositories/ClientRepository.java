package com.pruebadevsu.cliente_persona_service.repositories;

import com.pruebadevsu.cliente_persona_service.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
