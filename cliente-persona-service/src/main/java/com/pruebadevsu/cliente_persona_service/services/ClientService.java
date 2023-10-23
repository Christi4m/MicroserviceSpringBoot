package com.pruebadevsu.cliente_persona_service.services;

import com.pruebadevsu.cliente_persona_service.model.dtos.ClientRequest;
import com.pruebadevsu.cliente_persona_service.model.dtos.ClientResponse;
import com.pruebadevsu.cliente_persona_service.model.entities.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    public List<ClientResponse> findAll();

    public Optional<ClientResponse> findById(Long id);

    public ClientResponse save(ClientRequest clientRequest);

    public ClientResponse update(ClientRequest clientRequest, Long id);

    public void deleteById(Long id);


}
