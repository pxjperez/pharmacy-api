# Diseño: CRUD de sucursales

## Context

La API gestiona productos y usuarios con arquitectura Controller → Service → Repository y expone las entidades directamente en los controladores (ver `ProductoController`/`UsuarioController`). Este cambio replica ese mismo patrón para las sucursales sin nuevas dependencias.

## Goals / Non-Goals

**Goals:**
- CRUD de sucursales bajo `/api/sucursales` siguiendo el patrón existente (entidad directa, validación antes de persistir, 404 para inexistencia).
- Validaciones Bean Validation y documentación Swagger acordes a la spec `system-architecture`.

**Non-Goals:**
- Relación de sucursales con usuarios, productos o inventario.
- Unicidad de nombre/ciudad (no requerida en la spec).

## Decisions

### Exponer la entidad directamente en el controlador
- **Decisión**: `SucursalController` recibe y devuelve `SucursalEntity` (mismo patrón que `ProductoController`). Sin DTOs.
- **Por qué**: Es la convención del proyecto (confirmada tras el cambio `crud-usuario`); no hay datos sensibles que ocultar.
- **Alternativa considerada**: DTOs. Descartada por innecesaria.

### Campos y validación
- **Decisión**: `SucursalEntity` con `id`, `nombre` (obligatorio, máx. 120), `ciudad` (obligatorio, máx. 120), `direccion` (máx. 500) y `telefono` (máx. 20). `nombre` y `ciudad` con `@NotBlank`; el resto opcional.
- **Por qué**: Coincide con la densidad de validación usada en productos y es suficiente para el negocio.
- **Alternativa considerada**: Teléfono con `@Pattern` estricto. Descartado por no acotar formatos en el repositorio educacional.

### Errores reutilizando la infraestructura existente
- **Decisión**: `obtener`/`actualizar`/`eliminar` lanzan `RecursoNoEncontradoException` (404, ya mapeada) y la entrada inválida se resuelve con `@Valid` (400, ya mapeada).
- **Por qué**: No se necesita ampliar `ApiExceptionHandler`.

## Risks / Trade-offs

- [Cambio futuro: vincular usuarios a sucursales] → Es un cambio de spec aparte (`users` o `sucursales`), no bloquea este CRUD.
- [Sin restricción de unicidad en nombre/ciudad] → Aceptado; de requerirse, se agrega con 409 en un cambio posterior.

## Migration Plan

- No hay datos que migrar; Hibernate (`ddl-auto: update`) crea la tabla `sucursales`.
- Rollback: revertir el commit; los endpoints de productos y usuarios no cambian.

## Open Questions

- Ninguna: los supuestos (campos definidos, sin relación con usuarios, sin unicidad) quedan registrados en este diseño y en la delta spec.