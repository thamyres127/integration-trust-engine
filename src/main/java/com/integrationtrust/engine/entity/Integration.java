
package com.integrationtrust.engine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "integrations")
public class Integration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String name;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String sourceSystem;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String targetSystem;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String technicalOwner;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String certificateSerialNumber;

    @NotNull
    @Column(nullable = false)
    private LocalDate certificateExpirationDate;

    @NotBlank
    @Column(nullable = false, length = 30)
    private String operationalStatus;

    public Integration() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getTargetSystem() {
        return targetSystem;
    }

    public void setTargetSystem(String targetSystem) {
        this.targetSystem = targetSystem;
    }

    public String getTechnicalOwner() {
        return technicalOwner;
    }

    public void setTechnicalOwner(String technicalOwner) {
        this.technicalOwner = technicalOwner;
    }

    public String getCertificateSerialNumber() {
        return certificateSerialNumber;
    }

    public void setCertificateSerialNumber(String certificateSerialNumber) {
        this.certificateSerialNumber = certificateSerialNumber;
    }

    public LocalDate getCertificateExpirationDate() {
        return certificateExpirationDate;
    }

    public void setCertificateExpirationDate(LocalDate certificateExpirationDate) {
        this.certificateExpirationDate = certificateExpirationDate;
    }

    public String getOperationalStatus() {
        return operationalStatus;
    }

    public void setOperationalStatus(String operationalStatus) {
        this.operationalStatus = operationalStatus;
    }
}
