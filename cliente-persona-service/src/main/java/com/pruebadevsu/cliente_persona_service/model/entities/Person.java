package com.pruebadevsu.cliente_persona_service.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Person {

    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String gender;
    @Column(length = 50, nullable = false)
    private int age;
    @Column(length = 50, name = "id_number", nullable = false)
    private String idNumber;
    @Column(length = 50, nullable = false)
    private String address;
    @Column(length = 50, nullable = false)
    private String phone;

}
