package com.pruebadevsu.cuenta_movimientos_service.controllers;

import com.pruebadevsu.cuenta_movimientos_service.exceptions.BadRequestException;
import com.pruebadevsu.cuenta_movimientos_service.exceptions.NotFoundException;
import com.pruebadevsu.cuenta_movimientos_service.model.dtos.AccountRequest;
import com.pruebadevsu.cuenta_movimientos_service.model.dtos.AccountResponse;
import com.pruebadevsu.cuenta_movimientos_service.services.AccountService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cm/cuentas")
@RequiredArgsConstructor
@Slf4j
public class AccountController {



    private final AccountService service;


    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @CircuitBreaker(name = "cm-service", fallbackMethod = "saveAccountFallBack")
    public ResponseEntity<?> save(@RequestBody AccountRequest accountRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(accountRequest));

    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<AccountResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<AccountResponse> account = service.findById(id);

        if (!account.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(account);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@RequestBody AccountRequest accountRequest, @PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(service.update(accountRequest, id));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {

        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.deleteById(id);

        return ResponseEntity.ok().build();
    }


    private ResponseEntity<?> saveAccountFallBack(AccountRequest accountRequest, Throwable throwable){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
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
