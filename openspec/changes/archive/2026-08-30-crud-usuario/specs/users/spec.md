# Delta spec: Users

## Purpose

Gestión de las cuentas de usuario del sistema de farmacia (creación, consulta, actualización y eliminación) para administrar quién puede acceder al catálogo de productos.

## ADDED Requirements

### Requirement: Crear usuario
El sistema SHALL crear un usuario válido y responder HTTP 201 con su identificador, sin exponer la contraseña.

#### Scenario: Usuario válido
- **GIVEN** un nombre de usuario, email, contraseña y rol válidos
- **WHEN** el cliente envía `POST /api/usuarios`
- **THEN** el usuario queda persistido con su contraseña cifrada y la respuesta contiene su ID

#### Scenario: Usuario inválido
- **GIVEN** un usuario sin nombre, con email inválido o sin contraseña
- **WHEN** el cliente envía `POST /api/usuarios`
- **THEN** la API responde HTTP 400 y no persiste el usuario

#### Scenario: Nombre de usuario duplicado
- **GIVEN** que ya existe un usuario con el mismo nombre de usuario
- **WHEN** el cliente envía `POST /api/usuarios`
- **THEN** la API responde HTTP 409 y no persiste el usuario

### Requirement: Listar usuarios
El sistema SHALL listar los usuarios y obtener uno por su identificador, sin exponer contraseñas.

#### Scenario: Listar todos los usuarios
- **GIVEN** que existen usuarios registrados
- **WHEN** el cliente envía `GET /api/usuarios`
- **THEN** la API responde HTTP 200 con la lista de usuarios

#### Scenario: Obtener usuario por id
- **GIVEN** que existe un usuario en el sistema
- **WHEN** el cliente envía `GET /api/usuarios/{id}`
- **THEN** la API responde HTTP 200 con el usuario correspondiente

### Requirement: Actualizar usuario
El sistema SHALL actualizar los datos de un usuario existente.

#### Scenario: Actualizar usuario existente
- **GIVEN** un usuario existente
- **WHEN** el cliente envía `PUT /api/usuarios/{id}` con datos actualizados
- **THEN** la API actualiza el usuario y responde HTTP 200

### Requirement: Eliminar usuario
El sistema SHALL eliminar un usuario existente y responder HTTP 204.

#### Scenario: Eliminar usuario existente
- **GIVEN** un usuario existente
- **WHEN** el cliente envía `DELETE /api/usuarios/{id}`
- **THEN** la API elimina el usuario y responde HTTP 204

### Requirement: Usuario inexistente
El sistema SHALL responder HTTP 404 cuando el identificador no exista.

#### Scenario: Usuario no encontrado
- **GIVEN** que no existe un usuario para el identificador dado
- **WHEN** el cliente envía una solicitud para `/api/usuarios/{id}`
- **THEN** la API responde HTTP 404

### Requirement: No exponer contraseñas
El sistema SHALL omitir la contraseña en todas las respuestas de la API.

#### Scenario: Respuestas sin contraseña
- **GIVEN** un usuario persistido con contraseña
- **WHEN** el cliente recibe cualquier respuesta de `/api/usuarios`
- **THEN** la contraseña no aparece en la respuesta