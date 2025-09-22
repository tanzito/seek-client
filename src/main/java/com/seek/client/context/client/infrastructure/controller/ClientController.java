package com.seek.client.context.client.infrastructure.controller;

import com.seek.client.context.client.domain.ClientService;
import com.seek.client.context.client.domain.dto.ClientDto;
import com.seek.client.context.client.domain.dto.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
@Tag(name = "API Client", description = "client services")
@Slf4j
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ApiResponse(responseCode = "200", description = "Operación exitosa")
    @ApiResponse(responseCode = "422", description = "Error de petición")
    @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    @Operation(summary = "Recupera un cliente por Id", description = "Recupera un cliente dado un id de tipo numérico")
    @GetMapping("{id}")
    ResponseEntity<ClientDto> findById(
            @Parameter(description = "Id de cliente. Valor entero", required = true, example = "1")
            @PathVariable Integer id) {
        log.info("finding client by id {}", id);
        return ResponseEntity.ok(this.clientService.finById(id));
    }

    @ApiResponse(responseCode = "200", description = "Operación exitosa")
    @ApiResponse(responseCode = "400", description = "Error de petición")
    @Operation(summary = "Guardar un cliente", description = "")
    @PostMapping()
    ResponseEntity<ClientDto> create(@RequestBody @Valid() ClientSaveRequest clientSaveRequest) {
        log.info("creating client {}", clientSaveRequest);
        return ResponseEntity.ok(this.clientService.save(clientSaveRequest.toModel()));
    }

    @ApiResponse(responseCode = "200", description = "Operación exitosa")
    @ApiResponse(responseCode = "400", description = "Error de petición")
    @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    @Operation(summary = "Listar clientes paginados", description = "Recupera los clientes por páginas.")
    @GetMapping
    public ResponseEntity<PageResponse<ClientDto>> getUsers(
            @Parameter(description = "Número de página", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamaña de página", example = "10") @RequestParam(defaultValue = "10") int size
    ) {
        log.info("getting clients by pages , page {} y size {}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        PageResponse<ClientDto> result = this.clientService.findAll(pageable);
        return ResponseEntity.ok(result);
    }
}
