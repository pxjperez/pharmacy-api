# Delta spec: Registro de producto

## ADDED Requirements

### Requirement: Registro validado
El sistema SHALL rechazar productos sin nombre, con precio negativo o con stock negativo.

#### Scenario: Precio negativo
- **GIVEN** un producto con precio menor que cero
- **WHEN** el cliente intenta registrarlo
- **THEN** la API responde HTTP 400 y no persiste el producto
