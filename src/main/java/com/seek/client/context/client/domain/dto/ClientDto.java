package com.seek.client.context.client.domain.dto;

import com.seek.client.context.client.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {
    private Integer id;
    private String name;
    private String lastName;
    private LocalDate birthdate;
    private Integer age;
    private Integer lifeExpectancy;

    public ClientDto(Client client, Integer fixedLifeExpectancy) {
        this.id = client.getId();
        this.name = client.getName();
        this.lastName = client.getLastName();
        this.birthdate = client.getBirthdate();
        this.age = client.getAge();
        this.lifeExpectancy =  Math.max(fixedLifeExpectancy - this.age, 0);
    }
}
