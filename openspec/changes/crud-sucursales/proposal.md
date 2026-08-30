# Propuesta: CRUD de sucursales

## Why
La farmacia necesita gestionar sus puntos de venta físicos (sucursales) para administrar dónde se ubica y dónde opera el negocio, siguiendo el mismo patrón de CRUD del catálogo de productos y usuarios.

## What Changes
- Se crea la entidad `Sucursal` con id, nombre, ciudad, dirección y teléfono.
- Se agregan los endpoints `POST /api/sucursales`, `GET /api/sucursales`, `GET /api/sucursales/{id}`, `PUT /api/sucursales/{id}` y `DELETE /api/sucursales/{id}`.
- Se expone la entidad directamente en el controlador (mismo patrón que productos y usuarios), con validaciones Bean Validation y documentación Swagger.

## Capabilities

### New Capabilities
- `sucursales`: Gestión de las sucursales de la farmacia (crear, listar, obtener, actualizar y eliminar).

### Modified Capabilities
No se modifican capabilities existentes.

## Impact
- Código: nuevas capas entity, repository, service y controller para `Sucursal` (`src/main/java/com/ejemplo/api/...`).
- Contrato: nuevo conjunto de endpoints bajo `/api/sucursales`, documentados en Swagger.
- Dependencias: ninguna nueva.