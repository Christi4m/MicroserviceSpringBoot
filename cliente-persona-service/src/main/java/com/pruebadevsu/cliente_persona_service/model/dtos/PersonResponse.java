package com.pruebadevsu.cliente_persona_service.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class PersonResponse {

    private String name;
    private String gender;
    private int age;
    private String idNumber;
    private String address;
    private String phone;
}
