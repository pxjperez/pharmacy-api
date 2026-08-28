# Arquitectura del sistema

## Requisitos

### Requisito: Arquitectura por capas
El sistema SHALL separar entrada HTTP, lógica de aplicación y persistencia en Controller, Service y Repository.

#### Escenario: Solicitud REST
- **DADO** un cliente que invoca la API
- **CUANDO** el controlador recibe la solicitud
- **ENTONCES** delega la operación al servicio y este utiliza el repositorio

### Requisito: Persistencia
El sistema SHALL persistir productos en MySQL mediante Spring Data JPA.
