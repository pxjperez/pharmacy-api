# Delta spec: Authentication

## ADDED Requirements

### Requirement: Autenticar usuario
El sistema SHALL autenticar a un usuario con nombre de usuario y contraseña, y responder con un token de sesión.

#### Scenario: Credenciales válidas
- **GIVEN** un usuario registrado con credenciales válidas
- **WHEN** el cliente envía `POST /api/auth/login`
- **THEN** la API responde HTTP 200 con un token de sesión

#### Scenario: Credenciales inválidas
- **GIVEN** un usuario que proporciona credenciales incorrectas
- **WHEN** el cliente envía `POST /api/auth/login`
- **THEN** la API responde HTTP 401 y no emite token

### Requirement: Proteger endpoints de productos
El sistema SHALL exigir un token de sesión autenticado para acceder a los endpoints de gestión de productos.

#### Scenario: Solicitud no autenticada
- **GIVEN** una solicitud sin un token de sesión válido
- **WHEN** el cliente envía una solicitud a cualquier endpoint de `/api/productos`
- **THEN** la API responde HTTP 401 y no procesa la solicitud

#### Scenario: Solicitud autenticada
- **GIVEN** un token de sesión válido en la solicitud
- **WHEN** el cliente envía una solicitud a un endpoint de `/api/productos`
- **THEN** la API procesa la solicitud
