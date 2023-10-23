package com.pruebadevsu.cliente_persona_service.services;

import com.pruebadevsu.cliente_persona_service.helpers.BeanUtilsHelper;
import com.pruebadevsu.cliente_persona_service.model.dtos.ClientRequest;
import com.pruebadevsu.cliente_persona_service.model.dtos.ClientResponse;
import com.pruebadevsu.cliente_persona_service.model.mappers.ClientMapper;
import com.pruebadevsu.cliente_persona_service.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    private BeanUtilsHelper beanUtilsHelper;

    @Override
    @Transactional(readOnly = true)
    public List<ClientResponse> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(client -> ClientMapper.INSTANCE.clientToClientResponse(client))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClientResponse> findById(Long id) {
        return clientRepository.findById(id).map(client -> ClientMapper.INSTANCE.clientToClientResponse(client));
    }

    @Override
    @Transactional()
    public ClientResponse save(ClientRequest clientRequest) {
        var res = clientRepository.save(ClientMapper.INSTANCE.clientRequestToClient(clientRequest));
        return ClientMapper.INSTANCE.clientToClientResponse(res);
    }

    @Override
    @Transactional()
    public ClientResponse update(ClientRequest clientRequest, Long id) {

        Optional<ClientResponse> client = this.findById(id);

        if (!client.isPresent()) {
            throw new NoSuchElementException("Could not find client with id: " + id);
        }
        ClientResponse clientExist = client.get();

        beanUtilsHelper.copyNonNullProperties(clientExist, clientRequest);

        var res = clientRepository.save(ClientMapper.INSTANCE.clientResponseToClient(clientExist));

        return ClientMapper.INSTANCE.clientToClientResponse(res);
    }

    @Override
    @Transactional()
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

}
