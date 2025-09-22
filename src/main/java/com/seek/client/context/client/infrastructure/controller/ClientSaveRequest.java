package com.seek.client.context.client.infrastructure.controller;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.seek.client.context.client.domain.Client;
import com.seek.client.context.client.domain.dto.ClientSaveDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@JsonPropertyOrder({"name", "lastName", "birthdate"})
@Schema(description = "Dato de cliente")
@Data
public class ClientSaveRequest {
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 20)
    private String name;

    @NotNull
    @Size(min = 3, max = 20)
    @NotBlank(message = "El nombre no puede estar vacío")
    private String lastName;

    @NotNull
    @Past(message = "La fecha de nacimiento debe ser fecha pasada")
    private LocalDate birthdate;

    public Client toModel() {
        return Client.builder()
                .name(this.name)
                .lastName(this.lastName)
                .birthdate(this.birthdate)
                .build();
    }
}
