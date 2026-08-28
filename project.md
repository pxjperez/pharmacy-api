# Contexto del proyecto

API REST educativa para gestionar productos de una farmacia. La aplicación usa Java 21, Spring Boot, Maven, Spring Data JPA y MySQL. El repositorio combina historias de usuario para el contexto de negocio, OpenSpec para la intención técnica durable y OpenCode/Claude Code como agentes de implementación.

## Decisiones
- Base URL: `/api/productos`.
- Capas: controller, service, repository y entity.
- La base se actualiza mediante Hibernate solo para esta demo (`ddl-auto: update`).
- Los secretos incluidos son únicamente credenciales locales de demostración.
