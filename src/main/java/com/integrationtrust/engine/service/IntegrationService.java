
package com.integrationtrust.engine.service;

import com.integrationtrust.engine.entity.Integration;
import com.integrationtrust.engine.repository.IntegrationRepository;

import com.integrationtrust.engine.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class IntegrationService {

    private final IntegrationRepository repository;

    public IntegrationService(IntegrationRepository repository) {
        this.repository = repository;
    }

    // Cadastrar uma nova integracao
    public Integration create(Integration integration) {

        validateIntegration(integration);

        return repository.save(integration);
    }

    // Listar todas as integracoes
    @Transactional(readOnly = true)
    public List<Integration> findAll() {

        return repository.findAll();
    }

    // Consultar uma integracao pelo ID
  @Transactional(readOnly = true)
public Integration findById(Long id) {
    if (id == null || id <= 0) {
        throw new IllegalArgumentException(
            "ID da integracao invalido."
        );
    }

    return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Integracao nao encontrada: " + id
        ));
}
    // Atualizar uma integracao existente
    public Integration update(Long id, Integration updatedData) {

        Integration existing = findById(id);

        validateIntegration(updatedData);

        existing.setName(updatedData.getName());
        existing.setSourceSystem(updatedData.getSourceSystem());
        existing.setTargetSystem(updatedData.getTargetSystem());
        existing.setTechnicalOwner(updatedData.getTechnicalOwner());

        existing.setCertificateSerialNumber(
            updatedData.getCertificateSerialNumber()
        );

        existing.setCertificateExpirationDate(
            updatedData.getCertificateExpirationDate()
        );

        existing.setOperationalStatus(
            updatedData.getOperationalStatus()
        );

        return repository.save(existing);
    }

    // Excluir uma integracao
    public void delete(Long id) {

        Integration integration = findById(id);

        repository.delete(integration);
    }

    // Filtrar integracoes pela situacao operacional
    @Transactional(readOnly = true)
    public List<Integration> findByStatus(String status) {

        validateStatus(status);

        return repository.findByOperationalStatus(
            status.trim().toUpperCase()
        );
    }

    // Consultar certificados vencidos
    @Transactional(readOnly = true)
    public List<Integration> findExpiredCertificates() {

        return repository.findByCertificateExpirationDateBefore(
            LocalDate.now()
        );
    }

    // Consultar certificados que vencem nos proximos 30 dias
    @Transactional(readOnly = true)
    public List<Integration> findCertificatesExpiringSoon() {

        LocalDate today = LocalDate.now();
        LocalDate limit = today.plusDays(30);

        return repository.findByCertificateExpirationDateBetween(
            today,
            limit
        );
    }

    // Validar os dados da integracao
    private void validateIntegration(Integration integration) {

        if (integration == null) {
            throw new IllegalArgumentException(
                "Os dados da integracao sao obrigatorios."
            );
        }

        requireText(integration.getName(), "Nome");
        requireText(integration.getSourceSystem(), "Sistema de origem");
        requireText(integration.getTargetSystem(), "Sistema de destino");
        requireText(integration.getTechnicalOwner(), "Responsavel tecnico");

        requireText(
            integration.getCertificateSerialNumber(),
            "Numero de serie do certificado"
        );

        if (integration.getCertificateExpirationDate() == null) {
            throw new IllegalArgumentException(
                "A validade do certificado e obrigatoria."
            );
        }

        validateStatus(integration.getOperationalStatus());
    }

    // Validar campos de texto
    private void requireText(String value, String field) {

        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(
                field + " e obrigatorio."
            );
        }
    }

    // Validar situacoes permitidas
    private void validateStatus(String status) {

        if (status == null) {
            throw new IllegalArgumentException(
                "Situacao operacional obrigatoria."
            );
        }

        String normalized = status.trim().toUpperCase();

        if (!normalized.equals("ACTIVE")
                && !normalized.equals("INACTIVE")
                && !normalized.equals("MAINTENANCE")) {

            throw new IllegalArgumentException(
                "Situacao invalida. Utilize ACTIVE, INACTIVE ou MAINTENANCE."
            );
        }
    }
}
