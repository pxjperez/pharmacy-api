# Propuesta: Contrato OpenAPI persistente

## Why
El contrato `openspec/specs/openapi-contract.yaml` documenta la API, pero hoy puede quedar desactualizado tras implementar cambios (por ejemplo, `usuarios` y `sucursales` no están en él). Se necesita una regla durable que obligue a incorporar toda API nueva al contrato.

## What Changes
- Se agrega a `system-architecture` el requisito de que todo cambio que agregue, modifique o elimine endpoints, operaciones o esquemas DEBE actualizar `openspec/specs/openapi-contract.yaml`.
- No cambia código en `src/`; solo la spec maestra (vía sincronización al archivar).

## Capabilities

### New Capabilities
No se introducen capabilities nuevas.

### Modified Capabilities
- `system-architecture`: nuevo requisito "Contrato OpenAPI sincronizado" que exige reflejar toda API en `openapi-contract.yaml`.

## Impact
- Spec: `openspec/specs/system-architecture.md` gana el nuevo requisito al archivar el cambio.
- Documentación: regla asociada añadida en `AGENTS.md` para que los agentes la apliquen por defecto.
- Código: ninguno.