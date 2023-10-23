package com.pruebadevsu.cuenta_movimientos_service.controllers;


import com.pruebadevsu.cuenta_movimientos_service.exceptions.BadRequestException;
import com.pruebadevsu.cuenta_movimientos_service.exceptions.NotFoundException;
import com.pruebadevsu.cuenta_movimientos_service.model.dtos.TransactionRequest;
import com.pruebadevsu.cuenta_movimientos_service.model.dtos.TransactionResponse;
import com.pruebadevsu.cuenta_movimientos_service.services.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cm/movimientos")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final TransactionService service;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> save(@RequestBody TransactionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<TransactionResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<TransactionResponse> transaction = service.findById(id);

        if (!transaction.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(transaction);
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
