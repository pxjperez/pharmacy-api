# Delta spec: Registro de producto

## ADDED Requirements

### Requirement: Registro validado
The system SHALL reject products without a name, with a negative price, or with negative stock.

#### Scenario: Precio negativo
- **GIVEN** un producto con precio menor que cero
- **WHEN** el cliente intenta registrarlo
- **THEN** la API responde HTTP 400 y no persiste el producto
