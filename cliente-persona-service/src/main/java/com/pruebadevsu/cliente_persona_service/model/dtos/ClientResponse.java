package com.pruebadevsu.cliente_persona_service.model.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ClientResponse extends PersonResponse {
    private Long id;
    private String password;
    private String state;

}
