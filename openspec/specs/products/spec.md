# Products

## Purpose

El sistema gestiona un catálogo de productos de farmacia a través de una API REST, permitiendo a usuarios autenticados crear, leer, actualizar y eliminar productos, y recibir respuestas HTTP adecuadas para operaciones válidas e inválidas.

## Requirements

### Requirement: Crear producto
El sistema SHALL crear un producto válido y responder HTTP 201 con su identificador.

#### Scenario: Producto válido
- **GIVEN** un nombre, precio y stock válidos
- **WHEN** el cliente envía `POST /api/productos`
- **THEN** el producto queda persistido y la respuesta contiene su ID

#### Scenario: Producto inválido
- **GIVEN** un producto sin nombre, con precio negativo o con stock negativo
- **WHEN** el cliente envía `POST /api/productos`
- **THEN** la API responde HTTP 400 y no persiste el producto

### Requirement: Listar productos
El sistema SHALL listar los productos y obtener uno por su identificador.

#### Scenario: Listar todos los productos
- **GIVEN** que existen productos en el catálogo
- **WHEN** el cliente envía `GET /api/productos`
- **THEN** la API responde HTTP 200 con la lista de productos

#### Scenario: Obtener producto por id
- **GIVEN** que existe un producto en el catálogo
- **WHEN** el cliente envía `GET /api/productos/{id}`
- **THEN** la API responde HTTP 200 con el producto correspondiente

### Requirement: Actualizar producto
El sistema SHALL actualizar los datos de un producto existente.

#### Scenario: Actualizar producto existente
- **GIVEN** un producto existente
- **WHEN** el cliente envía `PUT /api/productos/{id}` con datos actualizados
- **THEN** la API actualiza el producto y responde HTTP 200

### Requirement: Eliminar producto
El sistema SHALL eliminar un producto existente y responder HTTP 204.

#### Scenario: Eliminar producto existente
- **GIVEN** un producto existente
- **WHEN** el cliente envía `DELETE /api/productos/{id}`
- **THEN** la API elimina el producto y responde HTTP 204

### Requirement: Producto inexistente
El sistema SHALL responder HTTP 404 cuando el identificador no exista.

#### Scenario: Producto no encontrado
- **GIVEN** que no existe un producto para el identificador dado
- **WHEN** el cliente envía una solicitud para `/api/productos/{id}`
- **THEN** la API responde HTTP 404
