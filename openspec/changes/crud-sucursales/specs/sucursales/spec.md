# Delta spec: Sucursales

## Purpose

Gestión de las sucursales de la farmacia (creación, consulta, actualización y eliminación) para administrar los puntos de venta físicos donde opera el negocio.

## ADDED Requirements

### Requirement: Crear sucursal
El sistema SHALL crear una sucursal válida y responder HTTP 201 con su identificador.

#### Scenario: Sucursal válida
- **GIVEN** un nombre, ciudad, dirección y teléfono válidos
- **WHEN** el cliente envía `POST /api/sucursales`
- **THEN** la sucursal queda persistida y la respuesta contiene su ID

#### Scenario: Sucursal inválida
- **GIVEN** una sucursal sin nombre o con datos incompletos
- **WHEN** el cliente envía `POST /api/sucursales`
- **THEN** la API responde HTTP 400 y no persiste la sucursal

### Requirement: Listar sucursales
El sistema SHALL listar las sucursales y obtener una por su identificador.

#### Scenario: Listar todas las sucursales
- **GIVEN** que existen sucursales en el sistema
- **WHEN** el cliente envía `GET /api/sucursales`
- **THEN** la API responde HTTP 200 con la lista de sucursales

#### Scenario: Obtener sucursal por id
- **GIVEN** que existe una sucursal en el sistema
- **WHEN** el cliente envía `GET /api/sucursales/{id}`
- **THEN** la API responde HTTP 200 con la sucursal correspondiente

### Requirement: Actualizar sucursal
El sistema SHALL actualizar los datos de una sucursal existente.

#### Scenario: Actualizar sucursal existente
- **GIVEN** una sucursal existente
- **WHEN** el cliente envía `PUT /api/sucursales/{id}` con datos actualizados
- **THEN** la API actualiza la sucursal y responde HTTP 200

### Requirement: Eliminar sucursal
El sistema SHALL eliminar una sucursal existente y responder HTTP 204.

#### Scenario: Eliminar sucursal existente
- **GIVEN** una sucursal existente
- **WHEN** el cliente envía `DELETE /api/sucursales/{id}`
- **THEN** la API elimina la sucursal y responde HTTP 204

### Requirement: Sucursal inexistente
El sistema SHALL responder HTTP 404 cuando el identificador no exista.

#### Scenario: Sucursal no encontrada
- **GIVEN** que no existe una sucursal para el identificador dado
- **WHEN** el cliente envía una solicitud para `/api/sucursales/{id}`
- **THEN** la API responde HTTP 404