# Propuesta: CRUD de usuarios

## Why
La farmacia necesita gestionar las cuentas de usuario del sistema (encargados y administradores) con operaciones de creación, consulta, actualización y eliminación, preparando el catálogo para el control de acceso.

## What Changes
- Se crea la entidad `Usuario` con id, nombre de usuario, email, contraseña y rol.
- Se agregan los endpoints `POST /api/usuarios`, `GET /api/usuarios`, `GET /api/usuarios/{id}`, `PUT /api/usuarios/{id}` y `DELETE /api/usuarios/{id}`.
- La contraseña se persiste con hash (BCrypt); nunca se devuelve en las respuestas.
- Los usuarios se gestionan exponiendo la entidad `Usuario` directamente en el controlador (mismo patrón que productos); la contraseña es de solo escritura.
- **BREAKING** (futuro): cuando se archive `add-login-system`, el login podrá consumir la entidad `Usuario` existente.

## Capabilities

### New Capabilities
- `users`: Gestión de usuarios del sistema (crear, listar, obtener, actualizar y eliminar cuentas de usuario).

### Modified Capabilities
No se modifican capabilities existentes.

## Impact
- Código: nuevas capas entity, repository, service y controller para `Usuario` (`src/main/java/com/ejemplo/api/...`).
- Contrato: nuevo conjunto de endpoints bajo `/api/usuarios`, documentados en Swagger.
- Dependencias: `spring-security-crypto` para el hash BCrypt.