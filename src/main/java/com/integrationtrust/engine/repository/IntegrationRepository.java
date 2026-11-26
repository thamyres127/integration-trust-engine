package com.integrationtrust.engine.repository;

import com.integrationtrust.engine.entity.Integration;
import com.integrationtrust.engine.enums.OperationalStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IntegrationRepository
        extends JpaRepository<Integration, Long> {

    List<Integration> findByOperationalStatus(
        OperationalStatus status
    );

    List<Integration> findByCertificateExpirationDateBefore(
        LocalDate date
    );

    List<Integration> findByCertificateExpirationDateBetween(
        LocalDate startDate,
        LocalDate endDate
    );

    List<Integration> findByTechnicalOwnerIgnoreCase(
        String technicalOwner
    );
}
