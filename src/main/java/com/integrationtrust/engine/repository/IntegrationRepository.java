
package com.integrationtrust.engine.repository;

import com.integrationtrust.engine.entity.Integration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IntegrationRepository
        extends JpaRepository<Integration, Long> {

    // Buscar integracoes pela situacao operacional
    List<Integration> findByOperationalStatus(String status);

    // Buscar certificados vencidos
    List<Integration> findByCertificateExpirationDateBefore(
            LocalDate date
    );

    // Buscar certificados que vencem em determinado periodo
    List<Integration> findByCertificateExpirationDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );

    // Buscar integracoes pelo responsavel tecnico
    List<Integration> findByTechnicalOwnerIgnoreCase(
            String technicalOwner
    );
}
