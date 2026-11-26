
INSERT INTO integrations (
    name,
    source_system,
    target_system,
    technical_owner,
    certificate_serial_number,
    certificate_expiration_date,
    operational_status
)
VALUES
(
    'API de Fornecedor',
    'Internal System',
    'External Platform',
    'Integration Team',
    'CERT-2026-001',
    '2026-12-10',
    'ACTIVE'
),
(
    'API de Autenticacao',
    'Identity Service',
    'Partner Gateway',
    'Security Team',
    'CERT-2026-002',
    '2026-11-20',
    'ACTIVE'
),
(
    'API de Relatorios',
    'Reporting System',
    'Data Platform',
    'Data Engineering',
    'CERT-2026-003',
    '2027-06-15',
    'MAINTENANCE'
);
