package com.pruebadevsu.cliente_persona_service.model.mappers;

import com.pruebadevsu.cliente_persona_service.model.dtos.ClientRequest;
import com.pruebadevsu.cliente_persona_service.model.dtos.ClientResponse;
import com.pruebadevsu.cliente_persona_service.model.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Client clientRequestToClient(ClientRequest clientRequest);
    Client clientResponseToClient(ClientResponse clientResponse);
    ClientResponse clientToClientResponse(Client client);

   }
