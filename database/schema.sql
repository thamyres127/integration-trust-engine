
CREATE TABLE IF NOT EXISTS integrations (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    name VARCHAR(150) NOT NULL,
    source_system VARCHAR(150) NOT NULL,
    target_system VARCHAR(150) NOT NULL,
    technical_owner VARCHAR(150) NOT NULL,

    certificate_serial_number VARCHAR(100) NOT NULL,
    certificate_expiration_date DATE NOT NULL,

    operational_status VARCHAR(30) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_operational_status
    CHECK (
        operational_status IN (
            'ACTIVE',
            'INACTIVE',
            'MAINTENANCE'
        )
    )
);
