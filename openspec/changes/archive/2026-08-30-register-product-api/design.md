# Diseño

`ProductoController` expone HTTP; `ProductoService` define el caso de uso; `ProductoServiceImpl` coordina reglas y errores; `ProductoRepository` abstrae JPA; `ProductoEntity` representa la tabla `productos`.

Las excepciones de recurso inexistente se traducen a HTTP 404 mediante `@RestControllerAdvice`.
