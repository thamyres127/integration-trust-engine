
package com.integrationtrust.engine.controller;

import com.integrationtrust.engine.entity.Integration;
import com.integrationtrust.engine.service.IntegrationService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/integrations")
public class IntegrationController {

    private final IntegrationService service;

    public IntegrationController(IntegrationService service) {
        this.service = service;
    }

    // Cadastrar uma integracao
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integration create(
            @Valid @RequestBody Integration integration) {

        return service.create(integration);
    }

    // Listar todas as integracoes
    @GetMapping
    public List<Integration> findAll() {

        return service.findAll();
    }

    // Consultar uma integracao pelo ID
    @GetMapping("/{id}")
    public Integration findById(@PathVariable Long id) {

        return service.findById(id);
    }

    // Atualizar uma integracao
    @PutMapping("/{id}")
    public Integration update(
            @PathVariable Long id,
            @Valid @RequestBody Integration integration) {

        return service.update(id, integration);
    }

    // Excluir uma integracao
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    // Filtrar por situacao operacional
    @GetMapping("/status/{status}")
    public List<Integration> findByStatus(
            @PathVariable String status) {

        return service.findByStatus(status);
    }

    // Consultar certificados vencidos
    @GetMapping("/certificates/expired")
    public List<Integration> findExpiredCertificates() {

        return service.findExpiredCertificates();
    }

    // Consultar certificados proximos do vencimento
    @GetMapping("/certificates/expiring-soon")
    public List<Integration> findCertificatesExpiringSoon() {

        return service.findCertificatesExpiringSoon();
    }
}
