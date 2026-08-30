# Arquitectura del sistema

## Requisitos

### Requisito: Arquitectura por capas
El sistema separa entrada HTTP, lógica de aplicación y persistencia en Controller, Service y Repository.

#### Escenario: Solicitud REST
- **DADO** un cliente que invoca la API
- **CUANDO** el controlador recibe la solicitud
- **ENTONCES** delega la operación al servicio y este utiliza el repositorio

### Requisito: Persistencia
El sistema persiste productos en MySQL mediante Spring Data JPA.

### Debes de usar swagger
Swagger debe de mostrarce en la Raiz
Todo cambio debe de incluir swagger
Debes de usar las anotaciones swagger considerando lo siguiente:
- NombreClaseController => @Tag(name = "NombreClase", description = "Controlador para la gestión de NombreClase del sistema")
- Metodo Controller => @Operation(summary = "Resumen del Metodo", description = "Descripcion de lo que hace el metodo")
- NombreClaseEntity => @Schema(name = "NombreClase", description = "Modelo de datos del NombreClase")

### Requisito: Contrato OpenAPI sincronizado
El sistema SHALL actualizar el contrato `openspec/specs/openapi-contract.yaml` cuando agregue, modifique o elimine endpoints, operaciones o esquemas; toda API nueva DEBE quedar reflejada en el contrato como parte del mismo cambio.

#### Escenario: Nueva API incorporada
- **DADO** un cambio implementado que agrega o modifica rutas, operaciones o esquemas
- **CUANDO** se completa el cambio
- **ENTONCES** `openapi-contract.yaml` incorpora la nueva API con sus esquemas y respuestas y sigue siendo un contrato válido

#### Escenario: Cambio interno
- **DADO** un cambio que solo modifica la implementación interna sin alterar la API
- **CUANDO** se completa el cambio
- **ENTONCES** el contrato `openapi-contract.yaml` no requiere actualización