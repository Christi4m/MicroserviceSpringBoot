package com.pruebadevsu.cuenta_movimientos_service.model.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ClientResponse {
    private Long id;
    private String password;
    private String state;
    private String name;
    private String gender;
    private int age;
    private String idNumber;
    private String address;
    private String phone;

}
