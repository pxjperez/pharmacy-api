# Diseño: Contrato OpenAPI persistente

## Context

El contrato `openspec/specs/openapi-contract.yaml` es la fuente de verdad de la API expuesta, pero no existe una obligación formal de mantenerlo al día. Este cambio agrega esa obligación como requisito de `system-architecture` y refuerza la conducta por defecto en `AGENTS.md`.

## Goals / Non-Goals

**Goals:**
- Garantizar que todo endpoint, operación o esquema nuevo quede reflejado en `openapi-contract.yaml` como parte del mismo cambio.
- Dejar la regla como requisito persistente de la spec maestra.

**Non-Goals:**
- Agregar las rutas de `usuarios`/`sucursales` al contrato (se hará en el cambio `crud-sucursales` y el ajuste del contrato de usuarios como tarea de mantenimiento).
- Modificar el validado del CLI ni el código de la aplicación.

## Decisions

### Regla en la spec maestra vía delta
- **Decisión**: el requisito "Contrato OpenAPI sincronizado" se agrega a `system-architecture` mediante delta spec y se sincroniza al archivar. No se edita la spec maestra directo (norma de `AGENTS.md`).
- **Por qué**: es el flujo estándar de OpenSpec y la regla sobrevive de forma durable.

### Regla de conducta por defecto en AGENTS.md
- **Decisión**: `AGENTS.md` incluye la instrucción de agregar toda API nueva al contrato en el mismo cambio.
- **Por qué**: los agentes (y cualquier trayectoria) leen `AGENTS.md` al inicio de cada sesión, lo que vuelve la acción "por defecto" sin recordatorios.
- **Alternativa considerada**: solo la spec maestra. Se descartó porque por sí misma no conduce al agente a actuar.

### Contrato como parte de tasks.md de cada cambio
- **Decisión**: los futuros `tasks.md` incluirán una tarea explícita "Actualizar `openapi-contract.yaml`".
- **Por qué**: hace verificable el cumplimiento de la regla en cada implementación.

## Risks / Trade-offs

- [Contrato desactualizado de usuarios] → No se repara en este cambio (es mera especificación); quedará pendiente como tarea de mantenimiento y al adjuntar endpoints sucursales.
- [Formato de la spec maestra mixto (`### Requirement:` nuevo junto a `### Requisito:` existente)] → Aceptado: el validador exige el formato estándar en la delta.

## Migration Plan

- Al archivar, la delta sincroniza el nuevo requisito a `openspec/specs/system-architecture.md`.
- `AGENTS.md` ya está actualizado con la regla de conducta.
- Rollback: deshacer la edición en `AGENTS.md` y revertir el cambio antes del sync.

## Open Questions

- Ninguna.