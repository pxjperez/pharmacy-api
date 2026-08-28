# Instrucciones para agentes de IA

## Orden de contexto
1. Lee `project.md` y la historia de usuario aplicable.
2. Lee el estado maestro en `openspec/specs/`.
3. Para un cambio activo, lee `proposal.md`, `design.md`, `tasks.md` y sus delta specs.
4. Implementa una tarea a la vez y ejecuta `mvn test`.

## Reglas técnicas
- Java 21 y Spring Boot; arquitectura Controller → Service → Repository.
- Persistencia con Spring Data JPA y MySQL.
- No exponer lógica de negocio desde el controlador.
- Validar entradas y conservar el contrato OpenAPI.
- No modificar specs maestras directamente durante un cambio; archivar el cambio con OpenSpec.
