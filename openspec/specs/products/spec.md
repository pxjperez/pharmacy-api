# Capacidad: Productos

## Requisitos

### Requisito: Crear producto
El sistema SHALL crear un producto válido y responder HTTP 201 con su identificador.

#### Escenario: Producto válido
- **DADO** nombre, precio y stock válidos
- **CUANDO** se envía `POST /api/productos`
- **ENTONCES** el producto queda persistido y la respuesta contiene su ID

### Requisito: Consultar productos
El sistema SHALL listar productos y obtener uno por identificador.

### Requisito: Actualizar producto
El sistema SHALL actualizar los datos de un producto existente.

### Requisito: Eliminar producto
El sistema SHALL eliminar un producto existente y responder HTTP 204.

### Requisito: Producto inexistente
El sistema SHALL responder HTTP 404 cuando el identificador no exista.
