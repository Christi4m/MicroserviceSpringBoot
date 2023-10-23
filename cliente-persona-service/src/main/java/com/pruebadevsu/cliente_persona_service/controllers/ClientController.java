package com.pruebadevsu.cliente_persona_service.controllers;

import com.pruebadevsu.cliente_persona_service.exceptions.BadRequestException;
import com.pruebadevsu.cliente_persona_service.exceptions.NotFoundException;
import com.pruebadevsu.cliente_persona_service.model.dtos.ClientRequest;
import com.pruebadevsu.cliente_persona_service.model.dtos.ClientResponse;
import com.pruebadevsu.cliente_persona_service.services.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cp/clientes")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> save(@RequestBody ClientRequest clientRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(clientRequest));

    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<ClientResponse> findAll() {
        return this.clientService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<ClientResponse> client = clientService.findById(id);

        if (!client.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(client);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@RequestBody ClientRequest clientRequest, @PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(clientService.update(clientRequest, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {

        if (clientService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        clientService.deleteById(id);

        return ResponseEntity.ok().build();
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
