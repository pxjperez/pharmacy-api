# Delta spec: System Architecture

## ADDED Requirements

### Requirement: Contrato OpenAPI sincronizado
El sistema SHALL actualizar el contrato `openspec/specs/openapi-contract.yaml` cuando agregue, modifique o elimine endpoints, operaciones o esquemas; toda API nueva DEBE quedar reflejada en el contrato como parte del mismo cambio.

#### Escenario: Nueva API incorporada
- **DADO** un cambio implementado que agrega o modifica rutas, operaciones o esquemas
- **CUANDO** se completa el cambio
- **ENTONCES** `openapi-contract.yaml` incorpora la nueva API con sus esquemas y respuestas y sigue siendo un contrato válido

#### Escenario: Cambio interno
- **DADO** un cambio que solo modifica la implementación interna sin alterar la API
- **CUANDO** se completa el cambio
- **ENTONCES** el contrato `openapi-contract.yaml` no requiere actualización